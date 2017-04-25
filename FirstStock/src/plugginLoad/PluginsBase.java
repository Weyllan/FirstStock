/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugginLoad;



/**
 *
 * @author valentin
 */
public interface PluginsBase {
    
    /**
     * Obtient le libellé à afficher dans les menu ou autre pour le plugins
     * @return Le libellé sous forme de String. Ce libellé doit être clair et compréhensible facilement 
     */
    public String getLibelle();
    
    /**
    * Fonction de traitement principale du plugins de manipulation de la BDD
    */
    public void actionOnClic();
}
