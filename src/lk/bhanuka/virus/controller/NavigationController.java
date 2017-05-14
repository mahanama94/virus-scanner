/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.bhanuka.virus.controller;

import lk.bhanuka.virus.view.Main;

/**
 *
 * @author bhanuka
 */
public class NavigationController {
    
    public static void exit(){
        System.exit(0);
    }
    
    public static void launch(){
        Main mainFrame = new Main();
        mainFrame.setVisible(true);
    }
    
}
