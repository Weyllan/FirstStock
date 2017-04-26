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
     * 
     */
    
    /**
     * Obtient le libellé à afficher dans les menu ou autre pour le plugins
     * @return Le libellé sous forme de String. Ce libellé doit être clair et compréhensible facilement 
     */
    public String getLibelle();
    
    /**
     * Obtient la catégorie du plugins. Cette catégorie est celle dans laquelle le menu du plugins sera ajouté une fois chargé
     * @return
    */
    public int getCategorie();
}
