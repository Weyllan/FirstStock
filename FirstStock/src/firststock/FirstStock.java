/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firststock;


import IA.*;
import controller.DBAccess;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.UIManager;
import view.Window;

/**
 *
 * @author valentin
 */
public class FirstStock {

    
    /**
     * @param args the command line arguments
     */
    
    //private static String url = "jdbc:mysql://localhost:3306/mysql";
    private static String url = "jdbc:mysql://localhost:3306/StockData";
    private static String username = "root";


    private static String pwd = "mdp";

   // private static String pwd = "isencir";


    //private static String pwd = "isencir";
    //private static String pwd = "mdp";
    
    public static void main(String[] args) {
        
         
         
        //partie sarah
        //décommenter ça pour avoir le visu noir
        /*try

	{
	  UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel");
	}
	catch (Exception e)
	{
	  e.printStackTrace();
	}*/

        Window win = new Window("FirstStock");
        win.init();
        
        //partie de ?
        //Product[] p = {};
        //Order o = new Order(p, Transaction.VENTE);

        // TODO code application logic here
      
        
        //Partie de Valentin

        
        //accès pour sarah
       // DBAccess myDB = new DBAccess("jdbc:mysql://localhost:3306/mysql","root","isencir");


        DBAccess myDB = new DBAccess(url,username,pwd);
        
        /*try{
            myDB.setMyCash(500);
        }catch(SQLException e){
            
        }*/
        

  
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

}
