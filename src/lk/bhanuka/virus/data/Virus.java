/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.data;

/**
 *
 * @author bhanuka
 */
public class Virus {
    
    private String hash;
    
    private String name;
    
    public Virus(String hash, String name){
        this.hash = hash;
        this.name = name;
        System.out.println("Gettting from database name : "+ name +" Hash: "+ hash);
    }
    
    public String getHash(){
        return this.hash;
    }
    
    public String getName(){
        return this.name;
    }
}
