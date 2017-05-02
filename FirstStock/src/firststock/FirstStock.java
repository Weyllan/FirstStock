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
import view.Window;

/**
 *
 * @author valentin
 */
public class FirstStock {

    // TODO : Add button action
    /**
     * @param args the command line arguments
     */
    
    private static String url = "jdbc:mysql://localhost:3306/StockData";
    private static String username = "root";
    private static String pwd = "CIR3JAVA";
    
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
        //DBAccess myDB = new DBAccess("jdbc:mysql://localhost:3306/mysql","root","isencir");


        DBAccess myDB = new DBAccess(url,username,pwd);


        // Ne jamais oublier le try catch avant une requète, j'ai sécurisé l'ensemble
        /*System.out.println("testVal");
        try{
            ArrayList test = myDB.selectAllRaws();
            System.out.println(test.get(0).toString());
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }*/

        
        // Ne jamais oublier le try catch avant une requète, j'ai sécurisé l'ensemble
     
        

        //Partie de Mathieu
        // ZONE DE TEST IA
        testIA IA = new testIA();
        IA.makePrediction(myDB);
        /*
        IA.makePrediction(DB);
        IA.makePrediction(DB);
        */
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
