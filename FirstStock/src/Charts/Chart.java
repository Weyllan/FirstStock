/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import IA.MyPair;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author louis
 */
public abstract class Chart {

    protected ArrayList<MyPair>[] values;
    
    public Chart(){
        
    }
    
    public void setValues(ArrayList<MyPair>[] v){
        values = v;
    }
    
    public Container createPanel(String test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
