/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhanuka
 */
public class VirusDatabase {
    
    private static FileReader fileReader = new FileReader();
    
    public static List<Virus> getViruses(){
        List returnList = new ArrayList();
        

        try {
            for(String line: fileReader.readFileLines("virus-definitions.data")){
                returnList.add(new Virus(line.split(" ")[0],line.split(" ")[1]));
            }
        } catch (IOException ex) {
            Logger.getLogger(VirusDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnList;
        
    }
    
}
