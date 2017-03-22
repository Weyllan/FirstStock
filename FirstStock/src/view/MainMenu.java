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
public class MainMenu extends JPanel {
    public JButton stock = new JButton("Stock");
    public JButton cash = new JButton("Trésorie");

    public MainMenu(){
        this.setLayout(new GridLayout(1, 2));     
        this.add(stock);
        this.add(cash);
        this.setVisible(true);
    }
    
        public void main(){

    }
}
