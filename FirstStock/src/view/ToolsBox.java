/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JToolBar;

/**
 *
 * @author kieffersarah
 */
public class ToolsBox extends JToolBar{
    
    public ToolsBox(String str){
        super(str);
        init();
    }
    
    public void init(){
        Dimension dim = new Dimension(50,50);
        
        //this.setAutoscrolls(true);
        //this.setMinimumSize(dim);
        //this.setPreferredSize(dim);
        
        
    }
        
    public void addItem(JToolBar t, JComponent c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        t.add(c, gc);
    }
}
