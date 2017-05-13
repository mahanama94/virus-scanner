/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.matching;

/**
 *
 * @author bhanuka
 */
public class MatchingRequest {
 
    private String file;
    
    private String match;
    
    public MatchingRequest(String file, String match){
        this.file = file;
        this.match = match;
    }
    
    public String getFileHash(){
        return this.file;
    }
    
    public String getMatchHash(){
        return this.match;
    }
}
