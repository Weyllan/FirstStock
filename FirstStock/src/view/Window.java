/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author kieffersarah
 */
public class Window extends JFrame {
    
    private int sizeX=600;
    private int sizeY=500;
    
    private MainMenu mainMenu = new MainMenu();
    private StockMenu stockMenu = new StockMenu();
    private CashMenu cashMenu = new CashMenu();
    public JMenuBar menuBar = new JMenuBar();
    
    private JButton notification = new JButton("Notifications");
    private JButton stock = new JButton("Stocks");
    private JButton cash = new JButton("Trésorerie");
    private JButton back = new JButton("Retour");
    
    public Window(String str){
        super(str);
    }
    
    public void main(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(getSizeX(), getSizeY());
        this.setLayout(new BorderLayout(5, 5));
        
        stock.addActionListener(new EventStock());
        cash.addActionListener(new EventCash());
        back.addActionListener(new EventBack());
        
        loadMainMenu();
        
    }

    /**
     * @return the sizeX
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * @param sizeX the sizeX to set
     */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * @return the sizeY
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * @param sizeY the sizeY to set
     */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    /**
     * @return the mainMenu
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * @param mainMenu the mainMenu to set
     */
    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * @return the stockMenu
     */
    public StockMenu getStockMenu() {
        return stockMenu;
    }

    /**
     * @param stockMenu the stockMenu to set
     */
    public void setStockMenu(StockMenu stockMenu) {
        this.stockMenu = stockMenu;
    }

    /**
     * @return the cashMenu
     */
    public CashMenu getCashMenu() {
        return cashMenu;
    }

    /**
     * @param cashMenu the cashMenu to set
     */
    public void setCashMenu(CashMenu cashMenu) {
        this.cashMenu = cashMenu;
    }


    /**
     * @return the notification
     */
    public JButton getNotification() {
        return notification;
    }

    /**
     * @param notification the notification to set
     */
    public void setNotification(JButton notification) {
        this.notification = notification;
    }
    
    /*
    * Retour au menu Principal
    */
    public class EventBack implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            Window.this.loadMainMenu();
        }
    }
    
    /*
    * Chargement du menu des Stocks
    */
    public class EventStock implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            Window.this.loadStockMenu();
        }
    }
    
    /*
    * Chargement du menu Trésorerie
    */
    public class EventCash implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent clic){
            Window.this.loadCashMenu();
        }
    }
    
    public void loadCashMenu(){
        cashMenu.add(menuBar, BorderLayout.NORTH);
        cashMenu.add(stock, BorderLayout.CENTER);     
        cashMenu.add(notification, BorderLayout.SOUTH);
        
        this.getContentPane().remove(mainMenu);
        this.setContentPane(cashMenu);
        this.getContentPane().validate();
        this.setVisible(true);
        
        System.out.println("view.Window.loadStockMenu()");
    }
    
    
    
    public void loadStockMenu(){
        
        stockMenu.add(menuBar, BorderLayout.NORTH);
        stockMenu.add(cash, BorderLayout.CENTER);     
        stockMenu.add(notification, BorderLayout.SOUTH);
        
        this.getContentPane().remove(mainMenu);
        this.setContentPane(stockMenu);
        this.getContentPane().validate();
        this.setVisible(true);
        
        System.out.println("view.Window.loadStockMenu()");
        
    }
    

    public void loadMainMenu(){
        
        mainMenu.add(menuBar, BorderLayout.NORTH);
        mainMenu.add(cash, BorderLayout.WEST);     
        mainMenu.add(stock, BorderLayout.EAST);
        mainMenu.add(notification, BorderLayout.SOUTH);
        
        this.setContentPane(mainMenu);
        this.setVisible(true);
    }
    
}
