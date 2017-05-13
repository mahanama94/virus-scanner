/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.dynamic;

import com.jezhumble.javasysmon.OsProcess;
import com.jezhumble.javasysmon.ProcessVisitor;

/**
 *
 * @author bhanuka
 */
class Visitor implements ProcessVisitor {

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
