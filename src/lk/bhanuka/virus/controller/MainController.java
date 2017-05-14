/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.bhanuka.virus.data.FileWriter;
import lk.bhanuka.virus.data.Virus;
import lk.bhanuka.virus.data.VirusDatabase;
import lk.bhanuka.virus.dynamic.DynamicAnalyzer;
import lk.bhanuka.virus.dynamic.Snapshot;
import lk.bhanuka.virus.dynamic.Visitor;
import lk.bhanuka.virus.main.Main;
import lk.bhanuka.virus.matching.HashGenerator;
import lk.bhanuka.virus.matching.MatchingEngine;
import lk.bhanuka.virus.matching.MatchingRequest;
import lk.bhanuka.virus.matching.MatchingResponse;

/**
 *
 * @author bhanuka
 */
public class MainController {
    
    private static MatchingEngine matchingEngine = new MatchingEngine();
    
    private static FileWriter fileWriter = new FileWriter();
    
    public static void startAnalysis(String filepath){
        try {            
            
            // match with the database
            List<Virus> viruses = VirusDatabase.getViruses();
            
            Virus matchingVirus = null;
            float matchingScore = 0.0f;
            
            for(Virus virus: viruses){
                MatchingRequest request = new MatchingRequest(HashGenerator.generate(filepath),virus.getHash());
                
                MatchingResponse response = matchingEngine.match(request);
                if(matchingScore< response.BasicMatch){
                    matchingVirus = virus;
                    matchingScore = response.BasicMatch;
                }
            }

            if(matchingVirus != null){
                writeToFile("Virus name : "+ matchingVirus.getName());
                writeToFile("Matching Score : "+ matchingScore);
            }
            else{
                writeToFile("No similar viruses found on the database");
            }
            
            // perform dynamic analysis
            DynamicAnalyzer da = new DynamicAnalyzer();
            da.start();

            
            Process process = Runtime.getRuntime().exec(filepath);
            InputStream is = process.getErrorStream();
                        
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            String line;
            
            while ((line = br.readLine()) != null) {
              System.out.println(line);
            }
            
            da.stop();

            process.destroy();
            
            writeToFile("Number of Snapshots taken : "+ da.getSnapshots().size());
            writeToFile("Process ran with PID : "+ da.getPid());
            
            for(Snapshot s: da.getSnapshots()){
                writeToFile("=============== Start of snapshot ===============");
                s.getProcessTree(da.getPid()).accept(new Visitor(), 0);
                writeToFile("================ End of snapshot ===============");
            }
            
        } catch (IOException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void writeToFile(String data){
        try {
            fileWriter.writeLine("results.data", data+"\n");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
