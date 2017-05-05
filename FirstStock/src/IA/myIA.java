/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;
import Matrix.*;

import Matrix.MatrixMathematics;
import Matrix.Matrix;
import Charts.Chart;
import Charts.LineChart;
import java.util.ArrayList;
import java.util.Scanner;
import controller.DBAccess;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import IA.MyPair;
import javax.swing.JPanel;
import java.math.BigDecimal;

/**
 *
 * @author mathieu
 */
public class myIA {

    ArrayList<Double> datetable = new ArrayList<Double>();
    ArrayList<Double> ventetable = new ArrayList<Double>();
    ArrayList<Double> res = new ArrayList<Double>();
    DBAccess db = new DBAccess("jdbc:mysql://localhost:3306/StockData","root","mdp");
    static int i = 0;
    double covariance = 0;
    double ecartypest = 0;
    double ecartypevt = 0;
    double a = 0;
    double b = 0;
    
    public myIA() {
        
    }

    public myIA(ArrayList<Double> datetable, ArrayList<Double> ventetable, ArrayList<Double> res, DBAccess myDB) {
        this.db = myDB;
        this.datetable = datetable;
        this.ventetable = ventetable;
        this.res = res;
    }

    public JPanel makePrediction() {
        
        try {
            optimiseWithInitialValueOf1();
        }
        catch(NoSquareException e) {
                e.getMessage();
            }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                
                ArrayList<MyPair> polyInterpol = new ArrayList<MyPair>();
                ArrayList<MyPair> tendancePlot = new ArrayList<MyPair>();

                Integer periodecalcul = 11;

                Date endDate = dateFormat.parse("2017-04-12"); // Date de fin (actuel)
                
                Calendar end = Calendar.getInstance();     
                end.setTime(endDate);
                
                Calendar start = Calendar.getInstance();
                start.setTime(endDate);
                start.add(Calendar.DATE, -periodecalcul);
                Integer i = 0;
                
                System.out.println(start.getTime());
                System.out.println(end.getTime());
                //System.out.println(end.getTime());
                //System.out.println(start.before(end));
                for (Date newDate = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), newDate = start.getTime()) {
                   
//System.out.print("Enumerate the HashMap: ");

                    /* Les clés */
                    //Enumeration e = myDB.getCmdForProduct("ordinateur").keys();
                    //while (e.hasMoreElements()) {
                    //  System.out.println(e.nextElement() + " ");
                    //}

                    /* Les valeurs */
 /*    Enumeration e = myDB.getCmdForProduct("ordinateur").elements();
                while ( e.hasMoreElements() )
                System.out.print( e.nextElement() + " ");
                System.out.println();
                     */
                       
 
                    //System.out.println("bite " + dateFormat.format(newDate));
                    datetable.add((double)i);
                    
                    if (i == periodecalcul-1) { 
                        System.out.println("Je pense que tu vas écouler " + (int)(datetable.get(i) * a + b) + " produits");
                        tendancePlot = makePointsWithEq(a, b, i); // renvoie une liste de points de la courbe calculée 
                    }
                     
                    //System.out.print("Entrée vos ventes: ");
                    //System.out.println(myDB.getCmdForProduct("ordinateur").get(dateFormat.format(newDate)));
                    
                    if (db.getCmdForProduct("ordinateur").get(dateFormat.format(newDate))!=null) { // Pour le produit demandé
                        ventetable.add((double)(Integer)db.getCmdForProduct("ordinateur").get(dateFormat.format(newDate)));
                        polyInterpol.add(new MyPair(datetable.get(i),ventetable.get(i)));
                    } else {
                        ventetable.add(0.);
                    }

                    res.add((datetable.get(i) - (sum(datetable)) / datetable.size()) * (ventetable.get(i) - (sum(ventetable)) / ventetable.size()));
                    covariance = sum(res) / ventetable.size();
                    ecartypest = 0;
                    ecartypevt = 0;
                    for (int j = 0; j <= i; j++) {
                        ecartypest += Math.pow((datetable.get(j) - (sum(datetable) / datetable.size())), 2);
                        ecartypevt += Math.pow((ventetable.get(j) - (sum(ventetable) / ventetable.size())), 2);
                    }
                    ecartypest /= ventetable.size();
                    ecartypevt /= ventetable.size();
                    ecartypest = Math.pow(ecartypest, 0.5);
                    ecartypevt = Math.pow(ecartypevt, 0.5);
                    a = covariance / (ecartypest * ecartypest);
                    b = sum(ventetable) / ventetable.size() - a * (sum(datetable) / datetable.size());
                    
                    
                    i += 1;                   
                    
                    // Affichage graphique
                    
                }
                return this.printAsChart(tendancePlot, polyInterpol);
            } catch (ParseException e) {
                e.getMessage();
            }

        } catch (SQLException e) {
            e.getMessage();

        }
        return null;
    }

    public double sum(ArrayList<Double> m) {
        double sum = 0;
        for (Double d : m) {
            sum += d;
        }
        return sum;
    }
    
    public JPanel printAsChart(ArrayList ... curves){
        LineChart c;
        c = new LineChart();
        c.setValues(curves);
        c.export("test.jpeg","BelleCourbe", "Jours", "Montant");
        return c.createPanel("BelleCourbe", "Jours", "Montant");  
    } 

    public ArrayList<MyPair> makePointsWithEq(double a, double b, int i){
         ArrayList<MyPair> Points = new ArrayList<MyPair>();
       for (double j = 0; j<=i; j++){
           Points.add(new MyPair(j,3.4*Math.pow(j,2)+-27.9*j+163.5828945314922));
       }
       return Points;
    }

public void optimiseWithInitialValueOf1() throws NoSquareException {
    double[][] x = new double[11][1];
    x[0][0] = 0;
    x[1][0] = 1;
    x[2][0] = 2;
    x[3][0] = 3;
    x[4][0] = 4;
    x[5][0] = 5;
    x[6][0] = 6;
    x[7][0] = 7;
    x[8][0] = 8;
    x[9][0] = 9;
    x[10][0] = 10;
    double[] y = new double[] { 150, 100, 125, 200, 150, 100, 50,100,150,200,250 };
    GaussNewton gaussNewton = new GaussNewton() {

        @Override
        public double findY(double x, double[] b) {
            // y = (x * a1) / (a2 + x)
            return b[2]*Math.pow(x,2)+x*b[1]+b[0];
        }
    };
    double[] b = gaussNewton.optimise(x, y, 3);
    //Assert.assertArrayEquals(new double[]{0.36, 0.56}, b, 0.01);
}
}