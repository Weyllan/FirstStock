/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.*;
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
        
        System.out.println(getDBConnection());
    }
    
    public void selectAllBDD() throws SQLException {

	Connection dbConnection = null;
	Statement statement = null;

	String selectTableSQL = "SELECT * from product_type";

	try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectTableSQL);

            while (rs.next()) {
                String productName = rs.getString("product_name");

		System.out.println("product name : " + productName);

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
}
    
    
    /*  Function wich return a connection  */
    private static Connection getDBConnection() {

	Connection dbConnection = null;

	try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Ok!");  
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


