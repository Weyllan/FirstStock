/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author kieffersarah
 */
public class Window extends JFrame implements MouseListener{
    private int sizeX=600;
    private int sizeY=500;
    private MainMenu mainMenu = new MainMenu();
    private StockMenu stockMenu = new StockMenu();
    private CashMenu cashMenu = new CashMenu();
    public JMenuBar menuBar = new JMenuBar();
    private JButton notification = new JButton("Notifications");
    
    public Window(String str){
        super(str);
    }
    
    public void main(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(getSizeX(), getSizeY());
        this.setLayout(new BorderLayout(5, 5));
        
        this.add(menuBar, BorderLayout.NORTH);
        this.add(getMainMenu(), BorderLayout.CENTER);
        this.add(getNotification(), BorderLayout.SOUTH);
        this.setVisible(true);
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
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
