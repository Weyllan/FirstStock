/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author kieffersarah
 */
public class MainMenu extends PluginStyle {
    
    private StockMenu stockMenu = null;
    private CashMenu cashMenu = null;
    private JButton stock = new JButton("Stocks");
    private JButton cash = new JButton("Trésorerie");
    
    public MainMenu(String name, WorkSpace workSpace, Window window){
        super(name, workSpace, window);
        this.cashMenu = new CashMenu("cash menu", workSpace, window);
        this.stockMenu = new StockMenu("stock menu", workSpace, window);
        this.initMainMenu();
        this.addElements();
        this.getWorkSpace().getContentPane().validate();
        this.setVisible(true);
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
            stockMenu.loadPlugin();
        }
    }
    
    /*
    * Chargement du menu Trésorerie
    */
    public class EventCash implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            cashMenu.loadPlugin();
        }
    }
    
    @Override
    public void addElements(){
        plugin.addItem(plugin, stock, 0, 0, 1, 1,GridBagConstraints.CENTER);
        plugin.addItem(plugin, cash, 1, 0, 1, 1,GridBagConstraints.CENTER);
    }
}

