/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChart extends Chart{
   
   public PieChart() {

   }
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "Alu" , new Double( 20 ) );  
      dataset.setValue( "Jus d'orange" , new Double( 40 ) );   
      dataset.setValue( "Bananes" , new Double( 40 ) );    
      dataset.setValue( "Basket" , new Double( 10 ) );     
      dataset.setValue( "Bana" , new Double( 40 ) );      
      dataset.setValue( "Ba" , new Double( 40 ) );      
      dataset.setValue( "Banane" , new Double( 40 ) );      
      dataset.setValue( "Banaes" , new Double( 40 ) );      
      dataset.setValue( "Banes" , new Double( 40 ) );      
      dataset.setValue( "Baanes" , new Double( 40 ) );      
      dataset.setValue( "Banes" , new Double( 40 ) );      
      dataset.setValue( "Baes" , new Double( 40 ) );   
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset, String title ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         title,   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   @Override
   public JPanel createPanel( String title ) {
      JFreeChart chart = createChart(createDataset( ), title );  
      
      ChartPanel chartPanel = new ChartPanel( chart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 350 ) );
      
      return chartPanel; 
   }
}