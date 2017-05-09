/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import IA.MyPair;
import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart extends Chart{

    public BarChart() {

    }

    @Override
    protected JFreeChart createChart( String title, String x, String y ) {
        
        JFreeChart barChart = this.makeJFreeChart(title, x, y);
        
        // Custom chart..
        barChart.setBackgroundPaint(Color.white);
        
        return barChart;
    }

    
    private JFreeChart makeJFreeChart( String title, String x, String y ){
        final JFreeChart barChart = ChartFactory.createBarChart(
            title,      // chart title
            x,                      // x axis label
            y,                      // y axis label
            this.createDataset(),   // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        return barChart;
    }
    
    private CategoryDataset createDataset( ) {
        final String obj1 = "Banane";        
        final String obj2 = "Smoothy";        
        final String obj3 = "Aluminium";    
        
        final String mounth1 = "Avril";        
        final String mounth2 = "Mai";        
        final String mounth4 = "Juillet";        
        final String mounth3 = "Juin";  
        
        final DefaultCategoryDataset dataset = 
        new DefaultCategoryDataset( );  

        dataset.addValue( 1.0 , obj1 , mounth1 );        
        dataset.addValue( 5.0 , obj1 , mounth2 ); 
        dataset.addValue( 5.0 , obj1 , mounth3 );          
        dataset.addValue( 3.0 , obj1 , mounth4 );         

        dataset.addValue( 5.0 , obj2 , mounth1 );       
        dataset.addValue( 10.0 , obj2 , mounth2 );        
        dataset.addValue( 4.0 , obj2 , mounth3 );      
        dataset.addValue( 6.0 , obj2 , mounth4 );  

        dataset.addValue( 4.0 , obj3 , mounth1 );        
        dataset.addValue( 3.0 , obj3 , mounth2 );        
        dataset.addValue( 6.0 , obj3 , mounth3 );        
        dataset.addValue( 2.0 , obj3 , mounth4 );               

        return dataset; 
    }
}




