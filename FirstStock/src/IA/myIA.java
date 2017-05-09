/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import Matrix.*;

import Matrix.MatrixMathematics;
import Matrix.Matrix;

import Charts.BarChart;

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
import java.util.Collections;
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

    ArrayList<Double> datetable = new ArrayList<Double>(); //Abscisse
    ArrayList<Double> ordtable = new ArrayList<Double>(); //Ordonnée
    ArrayList<Double> res = new ArrayList<Double>();
    //DBAccess db = new DBAccess("jdbc:mysql://localhost:3306/StockData","root","CIR3JAVA");
    DBAccess db = new DBAccess("jdbc:mysql://localhost:3306/mysql","root","isencir");
    //DBAccess db = new DBAccess("jdbc:mysql://localhost:3306/StockData", "root", "mdp");
    //DBAccess db = new DBAccess("jdbc:mysql://localhost:3306/mysql","root","isencir");
    static int i = 0;
    int degre = 2;

    public myIA() {

    }

    public myIA(ArrayList<Double> datetable, ArrayList<Double> ordtable, ArrayList<Double> res, DBAccess myDB, int degre) {
        this.db = myDB;
        this.datetable = datetable;
        this.ordtable = ordtable;
        this.res = res;
        this.degre = degre;
    }

    public JPanel makePredictionVente(String product) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {

                ArrayList<MyPair> polyInterpol = new ArrayList<MyPair>();
                ArrayList<MyPair> tendancePlot = new ArrayList<MyPair>();

                Integer periodecalcul = 11;
                Date endDate = dateFormat.parse("2017-04-11"); // Date de fin (actuel)

                Calendar end = Calendar.getInstance();
                end.setTime(endDate);

                Calendar start = Calendar.getInstance();
                start.setTime(endDate);
                start.add(Calendar.DATE, -periodecalcul);
                Integer i = 0;


               

                for (Date newDate = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), newDate = start.getTime()) {

                    datetable.add((double) i);

                    if (i == periodecalcul - 1) {
                        double coef[] = {0, 0};
                        try {
                            coef = optimiseWithInitialValueOf1(polyInterpol, degre);
                        } catch (NoSquareException e) {
                            e.getMessage();
                        }
                        //System.out.println("Je pense que tu vas écouler " + (int) (datetable.get(i) * a + b) + " produits");
                        tendancePlot = makePointsWithEq(coef, i, degre); // renvoie une liste de points de la courbe calculée 
                    }

                    // Trésorerie différent: pas besoin de produit
                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null) { // Pour le produit demandé

                        ordtable.add((double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)));
                        polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                    } else {
                        ordtable.add(0.);
                    }

         

                    i += 1;

                }
                 
      
              

                return this.printVente(tendancePlot, polyInterpol);

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

    public JPanel printVente(ArrayList... curves) {
        Chart c;

        c = new LineChart();
        c.setValues(curves);

        return (JPanel) c.createPanel("Ventes", "Mois", "Montant");

    }
    public JPanel printTresorerie(ArrayList... curves) {
        Chart c;

        c = new LineChart();
        c.setValues(curves);

        return (JPanel) c.createPanel("Trésorerie", "Mois", "Montant");
  
    }
    public JPanel printStock(ArrayList... curves) {
        Chart c;

        c = new BarChart();
        c.setValues(curves);

        return (JPanel) c.createPanel("Stock", "Produits", "Quantités");

    }

    public ArrayList<MyPair> makePointsWithEq(double coef[], int i, int degre) {
        ArrayList<MyPair> Points = new ArrayList<MyPair>();
        for (double j = 0; j <= i; j++) {
            if (degre == 2) {
                Points.add(new MyPair(j, coef[2] * Math.pow(j, 2) + coef[1] * j + coef[0]));
            }
            if (degre == 1) {
                Points.add(new MyPair(j, coef[1] * j + coef[0]));
            }
            //Points.add(new MyPair(j,3.4*Math.pow(j,2)+-27.9*j+163.5828945314922));
        }
        return Points;
    }

    public double[] optimiseWithInitialValueOf1(ArrayList<MyPair> polyInterpol, int degre) throws NoSquareException {
        double[] y = new double[polyInterpol.size()];
        double[][] x = new double[polyInterpol.size()][1];
        for (int i = 0; i < polyInterpol.size(); i++) {
            x[i][0] = polyInterpol.get(i).key();
            y[i] = polyInterpol.get(i).value();
        }
        GaussNewton gaussNewton = new GaussNewton() {

            @Override
            public double findY(double x, double[] b) {
                // y = (x * a1) / (a2 + x)
                if (degre == 2) {
                    return b[2] * Math.pow(x, 2) + x * b[1] + b[0];
                } else if (degre == 1) {
                    return x * b[1] + b[0];
                } else {
                    return 0;
                }
            }
        };
        double[] b = gaussNewton.optimise(x, y, degre + 1);
        return b;
    }

    public double[] optimiseWithInitialValueOf1Stock(ArrayList<MyPair> polyInterpol, int degre) throws NoSquareException {
        double[] y = new double[polyInterpol.size()];
        double[][] x = new double[polyInterpol.size()][1];
        for (int i = 0; i < polyInterpol.size(); i++) {
            x[i][0] = polyInterpol.get(i).key();
            y[polyInterpol.size() - i - 1] = polyInterpol.get(i).value();
        }
        GaussNewton gaussNewton = new GaussNewton() {

            @Override
            public double findY(double x, double[] b) {
                // y = (x * a1) / (a2 + x)
                if (degre == 2) {
                    return b[2] * Math.pow(x, 2) + x * b[1] + b[0];
                } else if (degre == 1) {
                    return x * b[1] + b[0];
                } else {
                    return 0;
                }
            }
        };
        double[] b = gaussNewton.optimise(x, y, degre + 1);
        return b;
    }

    public JPanel makePredictionStock(String raw) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {

                ArrayList<MyPair> polyInterpol = new ArrayList<MyPair>();
                ArrayList<MyPair> tendancePlot = new ArrayList<MyPair>();

                Integer periodecalcul = 11;
                String product = "ordinateur";
                Date endDate = dateFormat.parse("2017-04-11"); // Date de fin (actuel)

                Calendar end = Calendar.getInstance();
                end.setTime(endDate);

                Calendar start = Calendar.getInstance();
                start.setTime(endDate);
                start.add(Calendar.DATE, -periodecalcul);
                Integer i = 0;
                System.out.println(start.getTime());
                System.out.println(end.getTime());

                // stock: courbe a lire à l'envers!
                //Collections.reverse(ordtable);
                
                 Date oldDate=start.getTime();
                for (Date newDate = end.getTime(); end.after(start); end.add(Calendar.DATE, -1), newDate = end.getTime()) {
                    
                    datetable.add((double) i);
                    if (i==0){

                        ordtable.add((double) (Integer) db.selectARawStock(raw));
                        polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                    } else {
                        if (i == periodecalcul - 1) {
                            double coef[] = {0, 0};
                            try {
                                coef = optimiseWithInitialValueOf1(polyInterpol, degre);
                            } catch (NoSquareException e) {
                                e.getMessage();
                            }
                            
                            tendancePlot = makePointsWithEq(coef, i, degre); // renvoie une liste de points de la courbe calculée 
                        }
                        // Stock   
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) { // Pour le produit demandé
                            //stock actuel = stock du jours precedent en debut de journe - commande du jours precedent
                            //stock du jour pre ( en début de jour) = commande du jour prec + stock actuel 
                           
                            ordtable.add((double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)) +ordtable.get(i-1)-(double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)));
                            polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                        }
                        
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) == null) {
                        
                            ordtable.add(ordtable.get(i - 1)+(double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)));
                            polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                        }
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) == null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) {
                        
                            ordtable.add(ordtable.get(i - 1)-(double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)));
                            polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                        }
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) == null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) == null) {
                        
                            ordtable.add(ordtable.get(i - 1));
                            polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                        }
                    }
                 
                    i += 1;
                    oldDate = newDate;
                }

                
                

                return this.printStock(tendancePlot, polyInterpol);

            } catch (ParseException e) {
                e.getMessage();
            }

        } catch (SQLException e) {
            e.getMessage();

        }

        return null;
    }
    
    public JPanel makePredictionTresorerie() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {

                ArrayList<MyPair> polyInterpol = new ArrayList<MyPair>();
                ArrayList<MyPair> tendancePlot = new ArrayList<MyPair>();

                String raw = "clavier";
                Integer periodecalcul = 11;
                String product = "ordinateur";
                Date endDate = dateFormat.parse("2017-04-11"); // Date de fin (actuel)

                Calendar end = Calendar.getInstance();
                end.setTime(endDate);

                Calendar start = Calendar.getInstance();
                start.setTime(endDate);
                start.add(Calendar.DATE, -periodecalcul);
                Integer i = 0;
                System.out.println(start.getTime());
                System.out.println(end.getTime());

              
                for (Date newDate = end.getTime(); end.after(start); end.add(Calendar.DATE, -1), newDate = end.getTime()) {

                    datetable.add((double) i);

                    if (i == 0) {

                        ordtable.add(db.getMyCash());

                    } else {

                        if (i == periodecalcul - 1) {
                            double coef[] = {0, 0};
                            try {
                                coef = optimiseWithInitialValueOf1(polyInterpol, degre);
                            } catch (NoSquareException e) {
                                e.getMessage();
                            }
                            //System.out.println("Je pense que tu vas écouler " + (int) (datetable.get(i) * a + b) + " produits");
                            tendancePlot = makePointsWithEq(coef, i, degre); // renvoie une liste de points de la courbe calculée 
                        }

                        //trésorerie actuel =  trésorerie précédente + commande - achat
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) {
                            ordtable.add(ordtable.get(i - 1) - (double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)) * 20 + (double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)) * 20);
                        }
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) == null) {
                            ordtable.add(ordtable.get(i - 1) + (double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)) * 20);
                        }
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) == null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) {
                            ordtable.add(ordtable.get(i - 1) - (double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)) * 20);
                        }
                        if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) == null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) == null) {
                            ordtable.add(ordtable.get(i - 1));

                        }
                        polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
        
                    }
                    i += 1;
                }

                return this.printTresorerie(tendancePlot, polyInterpol);

            } catch (ParseException e) {
                e.getMessage();
            }

        } catch (SQLException e) {
            e.getMessage();

        }

        return null;
    }
}
