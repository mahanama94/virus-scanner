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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.bhanuka.virus.dynamic.DynamicAnalyzer;
import lk.bhanuka.virus.dynamic.Snapshot;
import lk.bhanuka.virus.dynamic.Visitor;
import lk.bhanuka.virus.main.Main;

/**
 *
 * @author bhanuka
 */
public class MainController {
    
    public static void startAnalysis(String filePath){
        try {
            
            DynamicAnalyzer da = new DynamicAnalyzer();
            da.start();

            
            Process process = Runtime.getRuntime().exec("/home/bhanuka/UnsetProxy.sh");
            InputStream is = process.getErrorStream();
                        
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            String line;
            
            while ((line = br.readLine()) != null) {
              System.out.println(line);
            }
            
            da.stop();

            process.destroy();
            
            for(Snapshot s: da.getSnapshots()){
                System.out.println("=== Start of snapshot ===");
                s.getProcessTree(da.getPid()).accept(new Visitor(), 0);
                System.out.println("==== End of snapshot ===");
            }
            
        } catch (IOException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
