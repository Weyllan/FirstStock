/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import Charts.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import plugginLoad.CashPlugin;


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
        // Init IA.getData(Chart)
        //        IA.draw
        // init chart
        
        
        Chart p = new LineChart();
        workSpace.setContentPane(p.createPanel("Test"));
    }
    
    public class EventAccess implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            cash.loadMenu();
        }
    }
    
    public void loadMenu(){
        plugin.addElements();
        plugin.getWorkSpace().getContentPane().removeAll();
        plugin.getWorkSpace().setContentPane(plugin);
        plugin.getWorkSpace().getContentPane().validate();
        plugin.getWorkSpace().setTitle(plugin.name);
        plugin.setVisible(true);
        plugin.getToolsBox().removeAll();
        //cash.loadPlugins();
        plugin.getToolsBox().validate();
        System.out.println("view.Window.load : " + name);
        if(this.window.files.size() > 0){
            this.window.pluginsLoader.setFiles(this.window.convertArrayListToArrayString(this.window.files));
            try {
                this.loadPlugins(this.window.pluginsLoader.loadAllCashPlugins());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void loadPlugins(CashPlugin[] cashplugins){
        for (int index = 0; index < cashplugins.length; index++) {
            this.window.cashPlugins.add(cashplugins[index]);
            PluginStyle c = new PluginStyle(cashplugins[index].getLibelle(), workSpace, window);
        }
    }
    
    @Override
    public void addToTools(){ 
        this.getWindow().addJMenu(button);
    }
}
