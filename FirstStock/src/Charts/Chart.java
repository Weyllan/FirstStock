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
    protected int height, width;
    
    
    public Chart(){
        height = 600;
        width = 350;
    }
    
    public void setValues(ArrayList<MyPair>[] v){
        values = v;
    }
    
    public Container createPanel(String test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Container createPanel(String test, String x, String y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
