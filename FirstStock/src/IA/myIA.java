/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import Matrix.*;

import Charts.Chart;
import Charts.LineChart;
import java.util.ArrayList;
import controller.DBAccess;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author mathieu
 */
public class myIA {

    ArrayList<Double> datetable = new ArrayList<Double>(); //Abscisse
    ArrayList<Double> ordtable = new ArrayList<Double>(); //Ordonnée

    DBAccess db;

    static int i = 0;
    int degre = 2;
    String url = "";
    String pwd = "";
    String username = "";
    File fileToExport = null;

    public myIA() {
        File file = new File("log.txt");
        FileReader fr;

        try {
            //Création de l'objet de lecture
            fr = new FileReader(file);
            String str = "";
            int i = 0, j = 0;
            //Lecture des données
            while ((i = fr.read()) != -1) {
                if ((char) i == ('\n')) {
                    switch (j) {
                        case 0:
                            this.url = str;
                            break;
                        case 1:
                            this.username = str;
                            break;
                        case 2:
                            this.pwd = str;
                            break;
                    }
                    j++;
                    str = "";
                } else {
                    str += (char) i;
                }
            }
            //Affichage
            System.out.println(str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(url);
        System.out.println(username);
        System.out.println(pwd);
        this.db = new DBAccess(this.url, this.username, this.pwd);
    }

    public myIA(ArrayList<Double> datetable, ArrayList<Double> ordtable, DBAccess myDB, int degre) {
        this.db = myDB;
        this.datetable = datetable;
        this.ordtable = ordtable;
        this.degre = degre;

        File file = new File("log.txt");
        FileReader fr;

        try {
            //Création de l'objet de lecture
            fr = new FileReader(file);
            String str = "";
            int i = 0, j = 0;
            //Lecture des données
            while ((i = fr.read()) != -1) {
                if ((char) i == ('\n')) {
                    switch (j) {
                        case 0:
                            this.url = str;
                            break;
                        case 1:
                            this.username = str;
                            break;
                        case 2:
                            this.pwd = str;
                            break;
                    }
                    j++;
                    str = "";
                } else {
                    str += (char) i;
                }
            }
            //Affichage
            System.out.println(str);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.db = new DBAccess(this.url, this.username, this.pwd);
    }

    //Courbe des ventes
    public JPanel makePredictionVente(String product) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            ArrayList<MyPair> polyInterpol = new ArrayList<MyPair>();
            ArrayList<MyPair> tendancePlot = new ArrayList<MyPair>();
            java.util.Date endDate = new java.util.Date();
            Integer periodecalcul = 11;
            dateFormat.format(endDate);

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
                    } catch (NonCarreException e) {
                        e.getMessage();
                    }
                    tendancePlot = makePointsWithEq(coef, i, degre); // renvoie une liste de points de la courbe calculée 
                }

                if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null) {
                    ordtable.add((double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)));
                } else {
                    ordtable.add(0.);
                }
                polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));

                i += 1;

            }

            return this.printVente(tendancePlot, polyInterpol);

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

        if (fileToExport != null) {
            c.export(fileToExport, "Ventes", "Mois", "Montant");
        }
        return (JPanel) c.createPanel("Ventes", "Mois", "Montant");

    }

    public JPanel printTresorerie(ArrayList... curves) {
        Chart c;

        c = new LineChart();
        c.setValues(curves);
        if (fileToExport != null) {
            c.export(fileToExport, "Trésorerie", "Mois", "Montant");
        }
        return (JPanel) c.createPanel("Trésorerie", "Mois", "Montant");

    }

    public JPanel printStock(ArrayList... curves) {
        Chart c;

        c = new LineChart();
        c.setValues(curves);
        if (fileToExport != null) {
            c.export(fileToExport, "Stock", "Produits", "Quantités");
        }
        return (JPanel) c.createPanel("Stock", "Produits", "Quantités");

    }

    public void setFile(File file) {
        fileToExport = file;
    }

    // On place les points du polynome de régression
    public ArrayList<MyPair> makePointsWithEq(double coef[], int i, int degre) {
        ArrayList<MyPair> Points = new ArrayList<MyPair>();
        for (double j = 0; j <= i; j += 0.3) {
            if (degre == 2) {
                Points.add(new MyPair(j, coef[2] * Math.pow(j, 2) + coef[1] * j + coef[0]));
            }
            if (degre == 1) {
                Points.add(new MyPair(j, coef[1] * j + coef[0]));
            }
        }
        return Points;
    }

    // On place les points du polynome régression dans le sens inverse
    public ArrayList<MyPair> makePointsWithEq2(double coef[], int i, int degre) {
        ArrayList<MyPair> Points = new ArrayList<MyPair>();
        for (double j = 0; j <= i; j += 0.3) {
            if (degre == 2) {
                Points.add(new MyPair(-j, coef[2] * Math.pow(-j, 2) + coef[1] * -j + coef[0]));
            }
            if (degre == 1) {
                Points.add(new MyPair(-j, coef[1] * -j + coef[0]));
            }
        }
        return Points;
    }

    public double[] optimiseWithInitialValueOf1(ArrayList<MyPair> polyInterpol, int degre) throws NonCarreException {
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

    public double[] optimiseWithInitialValueOf1Stock(ArrayList<MyPair> polyInterpol, int degre) throws NonCarreException {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            ArrayList<MyPair> polyInterpol = new ArrayList<MyPair>();
            ArrayList<MyPair> tendancePlot = new ArrayList<MyPair>();
            java.util.Date endDate = new java.util.Date();
            Integer periodecalcul = 11;
            dateFormat.format(endDate);

            String product = "ordinateur"; // Doit être encore généraliser pour un produit quelconque

            Calendar end = Calendar.getInstance();
            end.setTime(endDate);

            Calendar start = Calendar.getInstance();
            start.setTime(endDate);
            start.add(Calendar.DATE, -periodecalcul);
            Integer i = 0;
            System.out.println(start.getTime());
            System.out.println(end.getTime());

            Date oldDate = start.getTime();
            for (Date newDate = end.getTime(); end.after(start); end.add(Calendar.DATE, -1), newDate = end.getTime()) {

                datetable.add(-(double) i);
                if (i == 0) {

                    ordtable.add((double) (Integer) db.selectARawStock(raw));
                    polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                } else {
                    if (i == periodecalcul - 1) {
                        double coef[] = {0, 0};
                        try {
                            coef = optimiseWithInitialValueOf1(polyInterpol, degre);
                        } catch (NonCarreException e) {
                            e.getMessage();
                        }

                        tendancePlot = makePointsWithEq2(coef, i, degre);
                    }
                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) {

                        ordtable.add((double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)) + ordtable.get(i - 1) + (double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)));
                        polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                    }

                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) == null) {

                        ordtable.add(ordtable.get(i - 1) + (double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)));
                        polyInterpol.add(new MyPair(datetable.get(i), ordtable.get(i)));
                    }
                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) == null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) {

                        ordtable.add(ordtable.get(i - 1) + (double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)));
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

        } catch (SQLException e) {
            e.getMessage();

        }

        return null;
    }

    // Courbe de la trésorerie
    public JPanel makePredictionTresorerie() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            ArrayList<MyPair> polyInterpol = new ArrayList<MyPair>();
            ArrayList<MyPair> tendancePlot = new ArrayList<MyPair>();
            java.util.Date endDate = new java.util.Date();
            dateFormat.format(endDate);

            Integer periodecalcul = 11;
            String product = "ordinateur"; // Doit être encore généraliser pour un produit quelconque
            String raw = "clavier"; // Idem pour les matières première
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);

            Calendar start = Calendar.getInstance();
            start.setTime(endDate);
            start.add(Calendar.DATE, -periodecalcul);
            Integer i = 0;
            System.out.println(start.getTime());
            System.out.println(end.getTime());

            ArrayList myExistRaws = db.selectAllRaws();
            for (Date newDate = end.getTime(); end.after(start); end.add(Calendar.DATE, -1), newDate = end.getTime()) {
                datetable.add((double) i);

                if (i == 0) {

                    ordtable.add(db.getMyCash());

                } else {

                    if (i == periodecalcul - 1) {
                        double coef[] = {0, 0};
                        try {
                            // On récupère les coefficients du polynome de second ordre
                            coef = optimiseWithInitialValueOf1(polyInterpol, degre);
                        } catch (NonCarreException e) {
                            e.getMessage();
                        }
                        tendancePlot = makePointsWithEq2(coef, i, degre);
                    }

                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) {
                        ordtable.add(ordtable.get(i - 1) + (double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)) * 20 + (double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)) * 20);
                    }
                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) != null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) == null) {
                        ordtable.add(ordtable.get(i - 1) - (double) (Integer) db.getCmdForProduct(product).get(dateFormat.format(newDate)) * 20);
                    }
                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) == null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) != null) {
                        ordtable.add(ordtable.get(i - 1) + (double) (Integer) db.getBuyForRaw(raw).get(dateFormat.format(newDate)) * 20);
                    }
                    if (db.getCmdForProduct(product).get(dateFormat.format(newDate)) == null && db.getBuyForRaw(raw).get(dateFormat.format(newDate)) == null) {
                        ordtable.add(ordtable.get(i - 1));

                    }
                    polyInterpol.add(new MyPair(-datetable.get(i), ordtable.get(i)));

                }
                i += 1;
            }

            return this.printTresorerie(tendancePlot, polyInterpol);

        } catch (SQLException e) {
            e.getMessage();

        }

        return null;
    }
}
