/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import IA.MyPair;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author louis
 */
public abstract class Chart {

    protected ArrayList<MyPair>[] values;
    protected int height, width;
    
    
    public Chart(){
        height = 350;
        width = 600;
    }
    
    public void setValues(ArrayList<MyPair>[] v){
        values = v;
    }
        
    private void export(String path, String title , String x, String y){
        File file = new File( path ); 
        try{
            ChartUtilities.saveChartAsJPEG(file ,this.makeJFreeChart(title, x, y), this.width , this.height);
        }
        catch(IOException e){
             e.getMessage();
        }
    }
    public Container createPanel(String test) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Container createPanel(String test, String x, String y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JFreeChart makeJFreeChart(String title, String x, String y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
