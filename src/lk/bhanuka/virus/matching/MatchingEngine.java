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
public class MatchingEngine {

    private BasicMatching grader = new BasicMatching();
    
    
    public MatchingResponse match(MatchingRequest request){
        
        MatchingResponse response = new MatchingResponse();
        
        this.grader.match(request, response);
        
        return response;
    }
}
