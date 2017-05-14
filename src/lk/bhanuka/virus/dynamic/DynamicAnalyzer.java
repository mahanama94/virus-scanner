/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.dynamic;

import com.jezhumble.javasysmon.JavaSysMon;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bhanuka
 */
public class DynamicAnalyzer extends Thread{

    private JavaSysMon systemMonitor;
    
    private List<Snapshot> snapshots;
    
    private int pid;
    
    public DynamicAnalyzer(){
        super();
        this.systemMonitor = new JavaSysMon();
        this.snapshots = new ArrayList();
    }
    
    
    @Override
    public void run() {
        
        this.pid = this.systemMonitor.currentPid();
        
        while(true){
            try {
                this.snapshots.add(new Snapshot(this.systemMonitor.processTree()));
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DynamicAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<Snapshot> getSnapshots(){
        return this.snapshots;
    }
    
    public int getPid(){
        return this.pid;
    }
}
