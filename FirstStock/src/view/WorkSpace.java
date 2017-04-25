/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

/**
 *
 * @author kieffersarah
 */
public class WorkSpace extends JInternalFrame{

    private MainMenu mainMenu = null;
    public Window window = null;

    public WorkSpace(Window window){
        super("main Menu");
        this.window = window;
        this.setLayout(new BorderLayout(0, 0));
        init();
        this.add(mainMenu, BorderLayout.CENTER);
        this.setVisible(true);
        
    }
    
    public void init(){
        mainMenu = new MainMenu("main menu", this, window);
    }
}
