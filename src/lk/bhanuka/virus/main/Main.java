/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jezhumble.javasysmon.*;


/**
 *
 * @author bhanuka
 */
public class Main {
    
    public static void main(String[] args){

        try {
            
//            Process process = new ProcessBuilder("/home/bhanuka/UnsetProxy.sh").start();

            
            JavaSysMon systemMonitor = new JavaSysMon();
            System.out.println("OS : "+ systemMonitor.osName());
            System.out.println("CPUs: "+ systemMonitor.numCpus());
            System.out.println("Current Process: "+ systemMonitor.currentPid());
            System.out.println("Frequency : "+ systemMonitor.cpuFrequencyInHz());
 
            
            
            
            Process process = Runtime.getRuntime().exec("/home/bhanuka/UnsetProxy.sh");
            InputStream is = process.getErrorStream();
            
            systemMonitor.visitProcessTree(systemMonitor.currentPid(), new Visitor());
            
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            
            String line;
            
            System.out.printf("Output of running %s is:", Arrays.toString(args));
            
            systemMonitor.visitProcessTree(systemMonitor.currentPid(), new Visitor());
            
            while ((line = br.readLine()) != null) {
              System.out.println(line);
            }
//            OsProcess proc = systemMonitor.processTree();
//            for(Object child: proc.children()){
//                if(child != null){
//                    System.out.println("parent ID : "+ ((OsProcess)child).processInfo().getParentPid());
//                    System.out.println("name : "+ ((OsProcess)child).processInfo().getName());
//                    System.out.println("command : "+ ((OsProcess)child).processInfo().getCommand());
//                }
//            }
            process.destroy();
            
        } catch (IOException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}

class Visitor implements ProcessVisitor{

    @Override
    public boolean visit(OsProcess proc, int i) {
        System.out.println("Parent : "+ proc.processInfo().getParentPid()+" current : "+ proc.processInfo().getPid());
        System.out.println("Children : "+ proc.children().size());
        for(Object child: proc.children()){
            if(child != null){
                System.out.println("parent ID : "+ ((OsProcess)child).processInfo().getParentPid());
                System.out.println("name : "+ ((OsProcess)child).processInfo().getName());
                System.out.println("command : "+ ((OsProcess)child).processInfo().getCommand());
                System.out.println("Process Id : " + ((OsProcess)child).processInfo().getPid());
            }
        }
        return false;
    }
    
}

