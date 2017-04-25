/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mathieu
 */
public class testIA {
    
    ArrayList<Double> stocktable = new ArrayList<Double>();
    ArrayList<Double> ventetable = new ArrayList<Double>();
    ArrayList<Double> res = new ArrayList<Double>();
    static int i = 0;
    double covariance = 0;
    double ecartypest = 0;
    double ecartypevt = 0;
    double a = 0;
    double b = 0;

    public testIA(){
        
    }
    
    public testIA(ArrayList<Double> stocktable, ArrayList<Double> ventetable, ArrayList<Double> res) {
        this.stocktable = stocktable;
        this.ventetable = ventetable;
        this.res = res;
    }   
    
    public void makePrediction(){
        // RETIRER PREMIER POINT EN O O
        // ON PEUT SE RESTREINDRE A UNE FENETRE DE 1 MOIS 
        // VOIR MAGIC CARD MARKET
        // ALGORITHME DE GAUSS NEWTON ?
        System.out.println(a);
        System.out.println(b);
        Scanner reader = new Scanner(System.in); 
        System.out.print("Entrée le jour: ");
        stocktable.add(reader.nextDouble());
        if (i>0)
        System.out.println("Je pense que tu vas écouler " + (int)(stocktable.get(i)*a+b)  + " produits");
        
        System.out.print("Entrée vos ventes: ");
        ventetable.add(reader.nextDouble());
        
        res.add((stocktable.get(i)-(sum(stocktable))/stocktable.size())*(ventetable.get(i)-(sum(ventetable))/ventetable.size()));
        covariance = sum(res)/ventetable.size();
        System.out.println(covariance);
        ecartypest = 0;
        ecartypevt = 0;
        for (int j=0; j<=i; j++){
            ecartypest += Math.pow((stocktable.get(j)-(sum(stocktable)/stocktable.size())),2);
            ecartypevt += Math.pow((ventetable.get(j)-(sum(ventetable)/ventetable.size())),2);
       }
        ecartypest /=ventetable.size();
        ecartypevt /=ventetable.size();
        ecartypest = Math.pow(ecartypest,0.5);
        ecartypevt = Math.pow(ecartypevt,0.5);
        a = covariance/(ecartypest*ecartypest);
        b = sum(ventetable)/ventetable.size()-a*(sum(stocktable)/stocktable.size());
        System.out.println(a);
        System.out.println(b);
        i +=1;      
    }
    
    public double sum(ArrayList<Double> m){
        double sum = 0;
        for(Double d : m)
            sum += d;
        return sum;
    }
    
}
