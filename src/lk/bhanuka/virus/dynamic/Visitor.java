/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.dynamic;

import com.jezhumble.javasysmon.OsProcess;
import com.jezhumble.javasysmon.ProcessVisitor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.bhanuka.virus.data.FileWriter;

/**
 *
 * @author bhanuka
 */
public class Visitor implements ProcessVisitor {

    private FileWriter fileWriter = new FileWriter();
    
    @Override
    public boolean visit(OsProcess proc, int i) {

        this.writeToFile("Child process count :"+ proc.children().size());
        if(proc.children().size()>0){
            this.writeToFile("------ Start of child process data ------");
        }
        for(Object child: proc.children()){
            if(child != null){
                this.writeToFile("name : "+ ((OsProcess)child).processInfo().getName());
                this.writeToFile("command : "+ ((OsProcess)child).processInfo().getCommand());
                this.writeToFile("Process Id : " + ((OsProcess)child).processInfo().getPid());
                this.writeToFile("Physical memory at snapshot (Bytes) : "+((OsProcess)child).processInfo().getResidentBytes());
                this.writeToFile("Physical memory total process (Bytes) : "+ ((OsProcess)child).processInfo().getTotalBytes());
                this.writeToFile("User mode execution (miliseconds) : "+ ((OsProcess)child).processInfo().getUserMillis());
                this.writeToFile("Kernel mode execution (miliseconds) : "+ ((OsProcess)child).processInfo().getSystemMillis());
            }
        }
        if(proc.children().size()>0){
            this.writeToFile("------ End of child process data ------");
        }
        return false;
    }
    
    private void writeToFile(String data){
        try {
            this.fileWriter.writeLine("results.data", data+"\n");
        } catch (IOException ex) {
            Logger.getLogger(Visitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
