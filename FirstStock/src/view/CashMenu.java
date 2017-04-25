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
public class CashMenu extends PluginStyle{
    
    private CashMenu cash = this;
    
    public CashMenu(String name, WorkSpace workSpace, Window window){
        super(name, workSpace, window);
        this.init();
    }
    
    public void init(){
        button.addActionListener(new EventAccess());
    }
    
    public class EventAccess implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            cash.loadMenu();
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
        cash.loadPlugins();
        plugin.getToolsBox().validate();
        System.out.println("view.Window.load : " + name);
    }
    
    public void loadPlugins(){
        PluginStyle c1 = new PluginStyle("c1", workSpace, window);
        PluginStyle c2 = new PluginStyle("c2", workSpace, window);
        PluginStyle c3 = new PluginStyle("c3", workSpace, window);
    }
    
    @Override
    public void addToTools(){ 
        this.getWindow().addJMenu(button);
    }
}
