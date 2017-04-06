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
    
    public DBAccess(String url, String username, String mdp){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Ok!");            

            Connection conn = DriverManager.getConnection(url, username, mdp);

            System.out.println("Connection Succesfull");
            
        } catch (Exception e) {
            System.err.println("Connection error! ");
            System.err.println(e.getMessage());
        }
    }
    
}


