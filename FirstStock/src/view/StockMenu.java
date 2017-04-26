/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import plugginLoad.StockPlugin;

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
        plugin.getToolsBox().validate();
        System.out.println("view.Window.load : " + name);
        if(this.window.files.size() > 0){
            this.window.pluginsLoader.setFiles(this.window.convertArrayListToArrayString(this.window.files));
            try {
                this.loadPlugins(this.window.pluginsLoader.loadAllStockPlugins());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void loadPlugins(StockPlugin[] stockplugins){

        for (int index = 0; index < stockplugins.length; index++) {
            this.window.stockPlugins.add(stockplugins[index]);
            PluginStyle c = new PluginStyle(stockplugins[index].getLibelle(), workSpace, window);
        }
    }
    
    @Override
    public void addToTools(){ 
        this.getWindow().addJMenu(button);
    }
    
    
}
