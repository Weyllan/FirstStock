/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kieffersarah
 */
public class StockMenu extends PluginStyle{
    
    private StockMenu stock = this;
    
    public StockMenu(String name, WorkSpace workSpace, Window window){
        super(name, workSpace, window);
        this.init();
    }
    
    public void init(){
        button.addActionListener(new EventAccess());
    }
    
    public class EventAccess implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            stock.loadMenu();
        }
    }
    
    public void loadMenu(){
        plugin.addElements();
        plugin.getWorkSpace().getContentPane().remove(0);
        plugin.getWorkSpace().setContentPane(plugin);
        plugin.getWorkSpace().getContentPane().validate();
        plugin.getWorkSpace().setTitle(plugin.name);
        plugin.setVisible(true);
        plugin.getToolsBox().removeAll();
        stock.loadPlugins();
        plugin.getToolsBox().validate();
        System.out.println("view.Window.load : " + name);
    }
    
    public void loadPlugins(){
        PluginStyle s1 = new PluginStyle("s1", workSpace, window);
        PluginStyle s2 = new PluginStyle("s2", workSpace, window);
        PluginStyle s3 = new PluginStyle("s3", workSpace, window);
    }
    
    @Override
    public void addToTools(){ 
        this.getWindow().addJMenu(button);
    }
    
    
}
