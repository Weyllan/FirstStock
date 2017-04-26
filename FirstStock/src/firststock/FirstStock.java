/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firststock;

import IA.*;
import Models.*;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import controller.DBAccess;
import java.sql.SQLException;
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

        DBAccess myDB = new DBAccess("jdbc:mysql://localhost:3306/StockData","root","CIR3JAVA");

        // Ne jamais oublier le try catch avant une requète, j'ai sécurisé l'ensemble
        try{
            myDB.modifyRawStock("souris", 15);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        
        //Partie de Mathieu
        /* ZONE DE TEST IA
        testIA IA = new testIA();
        IA.makePrediction();
        IA.makePrediction();
        IA.makePrediction();
        */
    }

}
