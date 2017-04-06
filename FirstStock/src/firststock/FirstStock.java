/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firststock;

import Models.*;
import view.Window;

/**
 *
 * @author valentin
 */
public class FirstStock {
    // TODO : Add button action
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Window win = new Window("ta maman");
        win.main();*/
        Product [] p = {};
        Order o = new Order(p,"totoCorp", Transaction.VENTE);
    }
    
}
