/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firststock;

import Models.*;
/**
 *
 * @author valentin
 */
public class FirstStock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Product banane = new Product("BANANA", 150, 5);
        Product lait = new Product("MILK", 50, 175);
        Product kiwi = new Product("KIWI", 125 , 1);
        
        Product[] list = {banane, lait, kiwi};
        
        Product smoothy = new Product("SMOOTHY", 1000 , list,1);

        Product[] mesAchats = {smoothy, banane};
        
        Order facture = new Order(mesAchats,Transaction.ACHAT);
        System.out.println(facture.totalCost());
        // TODO code application logic here
    }
    
}
