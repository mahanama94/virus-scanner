/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author bhanuka
 */
public class FileWriter {
    
    private  BufferedWriter getBufferedWriter(File file) throws IOException{
        
        if(!file.exists()){
            file.createNewFile();
        }
        
        java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile(), true);
        
        return new BufferedWriter(fw);
    }
    
    public void writeLine(File file, String line) throws IOException{
        
        BufferedWriter bw = getBufferedWriter(file);
        bw.write(line);
        bw.close();
    }
    
    public void writeLine(String fileName, String line) throws IOException{
        
        this.writeLine(new File(fileName), line);
    }
}
