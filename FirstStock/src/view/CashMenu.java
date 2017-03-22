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
public class CashMenu extends JPanel{
    
    private JButton stock = new JButton("Stock");
    private JButton back = new JButton("Back");
    
    public CashMenu(){
        this.setLayout(new GridLayout(1, 2));     
        this.add(stock);
        this.add(back);
        this.setVisible(false);
    }

    /**
     * @return the stock
     */
    public JButton getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(JButton stock) {
        this.stock = stock;
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
    
}
