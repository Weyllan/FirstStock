/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenu;

/**
 *
 * @author kieffersarah
 */
public class MainMenu extends PluginStyle {
    
    private StockMenu stockMenu = null;
    private CashMenu cashMenu = null;
    private JButton stock = new JButton("Stocks");
    private JButton cash = new JButton("Trésorerie");
    private MainMenu main = this;
    
    public MainMenu(String name, WorkSpace workSpace, Window window){
        super(name, workSpace, window);
        this.cashMenu = new CashMenu("cash menu", workSpace, window);
        this.stockMenu = new StockMenu("stock menu", workSpace, window);
        this.init();
        this.initMainMenu();
        this.addElements();
        this.getWorkSpace().getContentPane().validate();
        this.setVisible(true);
    }
    
    
    public void init(){
        button.addActionListener(new EventAccess());
    }
    
    public class EventAccess implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            main.loadMenu();
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
    }
    
    
    @Override
    public void addToTools(){ 
        this.getWindow().addJMenu(button);
    }
    
    public void initMainMenu(){
        stock.addActionListener(new EventStock());
        cash.addActionListener(new EventCash());
    }

    /*
    * Chargement du menu des Stocks
    */
    public class EventStock implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            stockMenu.loadMenu();
        }
    }
    
    /*
    * Chargement du menu Trésorerie
    */
    public class EventCash implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            cashMenu.loadMenu();
        }
    }
    
    @Override
    public void addElements(){
        plugin.addItem(plugin, stock, 0, 0, 1, 1,GridBagConstraints.CENTER);
        plugin.addItem(plugin, cash, 1, 0, 1, 1,GridBagConstraints.CENTER);
    }
}

