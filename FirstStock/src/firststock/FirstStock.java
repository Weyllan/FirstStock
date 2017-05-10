/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firststock;


import controller.DBAccess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import view.Window;

/**
 *
 * @author valentin
 */
public class FirstStock {

    
    /**
     * @param args the command line arguments
     */
    private static String url ;
    private static String username ;

    private static String pwd ;
    //private static String pwd = "isencir";
    //private static String pwd = "mdp";
    
    public FirstStock(){
        File file = new File("log.txt");
        FileReader fr;

        try {
          //Création de l'objet de lecture
          fr = new FileReader(file);
          String str = "";
          int i = 0, j = 0;
          //Lecture des données
          while((i = fr.read()) != -1){
              if((char)i == ('\n')){
                 switch(j){
                    case 0:
                            url = str;
                            break;
                    case 1:
                            username = str;
                            break;
                    case 2:
                            pwd = str;
                            break;
                 }
                 j++;                
                 str  = "";
              }
              else
              str += (char)i;
          }
          //Affichage
          System.out.println(str);

        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {

        Window win = new Window("FirstStock");
        win.init();

        DBAccess myDB = new DBAccess(url,username,pwd);

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
