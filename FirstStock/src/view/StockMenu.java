/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author kieffersarah
 */
public class StockMenu extends JPanel{
    private JButton back = new JButton("Retour");
    private JButton cash = new JButton("Trésorie");
    
    public StockMenu(){
        this.setLayout(new GridLayout(1, 2));     
        this.add(back);
        this.add(cash);
        this.setVisible(false);
    }

    /**
     * @return the back
     */
    public JButton getBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setBack(JButton back) {
        this.back = back;
    }

    /**
     * @return the cash
     */
    public JButton getCash() {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(JButton cash) {
        this.cash = cash;
    }
}
