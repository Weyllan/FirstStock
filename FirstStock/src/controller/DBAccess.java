/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.*;
import java.util.Hashtable;
/**
 *
 * @author valentin
 */


public class DBAccess {
    
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PWD;
    
    public DBAccess(String url, String username, String mdp){
        this.DB_URL = url;
        this.DB_USER = username;
        this.DB_PWD = mdp;
    }
    
    /*  Getters sur la BDD  */
    
    /*  TODO Find Map in JAVA  */
    
    /*  Return Stock of ONE Product, wich is specified  */
    public int selectAProductStock(String product_type) throws SQLException {
        int mystock = -1;
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT product_stock FROM product_type WHERE product_name LIKE '"+product_type+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String productStock = rs.getString("product_stock");
                mystock = Integer.parseInt(productStock);
                System.out.println("product stock : " + productStock);
                return mystock;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mystock;
    }
    
    /* Return all stocks of all products */
    public Hashtable selectAllProductsStocks() throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        Hashtable ht = new Hashtable();
	String selectTableSQL = "SELECT * from product_type";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String productName = rs.getString("product_name");
                String productStock = rs.getString("product_stock");
		System.out.println("product name : " + productName);
                System.out.println("product stock : " + productStock);
                ht.put(productName, Integer.parseInt(productStock));             
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    /*  Return Stock of ONE Raw, wich is specified  */
    public int selectARawStock(String raw_type) throws SQLException {
        int mystock = -1;
	Connection dbConnection = null;
	Statement statement = null; 
	String selectTableSQL = "SELECT raw_stock FROM raw_type WHERE raw_name LIKE '"+raw_type+"'";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String productStock = rs.getString("raw_stock");
                mystock = Integer.parseInt(productStock);
                System.out.println("raw stock : " + productStock);
                return mystock;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mystock;
    }
    
    /* Return all stocks of all products */
    public Hashtable selectAllRawsStocks() throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        Hashtable ht = new Hashtable();
	String selectTableSQL = "SELECT * from raw_type";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String productName = rs.getString("raw_name");
                String productStock = rs.getString("raw_stock");
		System.out.println("raw name : " + productName);
                System.out.println("raw stock : " + productStock);
                ht.put(productName, Integer.parseInt(productStock)); 
            }
            return ht;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return ht;
    }
    
    /* Return all stocks of all products */
    public double getMyCash() throws SQLException {
	Connection dbConnection = null;
	Statement statement = null;
        double mymonney = -1;
	String selectTableSQL = "SELECT quantity_cash from cash";
	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String mycash = rs.getString("quantity_cash");
                System.out.println("quantity : " + mycash);
                mymonney = Integer.parseInt(mycash);
                return mymonney;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
	} finally {
            if (statement != null) {
		statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
    	}
        return mymonney;
    }
    
    
    /*  Function wich return a connection  */
    private static Connection getDBConnection() {
	Connection dbConnection = null;
	try {
            Class.forName("com.mysql.jdbc.Driver");  
	} catch (Exception e) {
            System.out.println(e.getMessage());
	}      
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            System.out.println("Connection Succesfull");
            return dbConnection;
        } catch (Exception e) {
            System.out.println(e.getMessage());
	}        
        return dbConnection;
    }
    
}


