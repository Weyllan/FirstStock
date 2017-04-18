/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author louis
 */
public class Unit implements Serializable{
    /* Penser à implenter une sérialisation de l'enum afin de rajouter des 
        unités par l'utilisateur
    */
    private String unit;
    private int power;
    
    public Unit(String u, int p){
        this.setUnit(u);
        this.setPower(p);
    }
    
    /* Getters & setters */

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    @Override
    public String toString(){
        return "Unit : "+unit+" with 10^"+power;
    }
}
