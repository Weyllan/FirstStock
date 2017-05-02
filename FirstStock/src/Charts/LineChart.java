/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import IA.MyPair;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart extends Chart{

    public LineChart() {

    }

    private JFreeChart createChart( String title, String x, String y ) {
        
        JFreeChart lineChart = this.makeJFreeChart(title, x, y);
        
        // Custom chart..
        lineChart.setBackgroundPaint(Color.white);

        final XYPlot plot = lineChart.getXYPlot();

        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);
        
        return lineChart;
    }
    
    private void export(String path, String title , String x, String y){
        File lineChart = new File( path ); 
        try{
            ChartUtilities.saveChartAsJPEG(lineChart ,this.makeJFreeChart(title, x, y), this.width , this.height);
        }
        catch(IOException e){
             e.getMessage();
        }
    }
    
    private JFreeChart makeJFreeChart( String title, String x, String y ){
        final JFreeChart lineChart = ChartFactory.createXYLineChart(
            title,      // chart title
            x,                      // x axis label
            y,                      // y axis label
            this.createDataset(),   // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        return lineChart;
    }
    
    private XYSeriesCollection  createDataset( ) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        // Acces tableau
        for(int i = 0 ; i < super.values.length ; i++){
            final XYSeries serie = new XYSeries(i);
            // Acces ArrayList
            for(int j = 0 ; j < super.values[i].size() ; j++){

                serie.add(super.values[i].get(j).key(), super.values[i].get(j).value());
            }
            dataset.addSeries(serie);
        }
        return dataset;
    }

    @Override
    public JPanel createPanel( String title , String x, String y) {
        JFreeChart chart = createChart(title, x, y );  

        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( this.width , this.height ) );

        return chartPanel; 
   }
}




