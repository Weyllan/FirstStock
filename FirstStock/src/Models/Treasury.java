/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author louis
 */
public class Treasury {
    // Valeur du compte courant
    private double value;
    // Plafond de découvert
    private double limit = 0;
    
    Treasury(double value, double limit){
        this.value = value;
    }
    Treasury(int value){
        this(value, 0);
    }
    
    // Le compte est-il en bonne santé ?
    boolean isOk(){
        return (value>=limit);
    }
}
