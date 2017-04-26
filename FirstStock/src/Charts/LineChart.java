/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
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

    private static JFreeChart createChart( XYSeriesCollection dataset, String title ) {
      final JFreeChart lineChart = ChartFactory.createXYLineChart(
            "title",      // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
    
    // Custom chart..
        lineChart.setBackgroundPaint(Color.white);

        final XYPlot plot = lineChart.getXYPlot();

        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        plot.setRenderer(renderer);
        
        return lineChart;
    }
   
    private static XYSeriesCollection  createDataset( ) {
      final XYSeries series1 = new XYSeries("First");
         series1.add(-11.0, 1.0);
         series1.add(-10.0, 4.0);
         series1.add(-9.0, 3.0);
         series1.add(-8.0, 5.0);
         series1.add(-7.0, 5.0);
         series1.add(-6.0, 7.0);
         series1.add(-5.0, 7.0);
         series1.add(-4.0, 8.0);
         series1.add(-3.0, 5.0);
         series1.add(-2.0, 10.0);
         series1.add(-1.0, 1.0);
         series1.add(0, 8.0);

         final XYSeries series2 = new XYSeries("Secd");
         series2.add(1.0, 5.0);
         series2.add(2.0, 7.0);
         series2.add(3.0, 6.0);
         series2.add(4.0, 8.0);
         series2.add(5.0, 4.0);
         series2.add(6.0, 4.0);
         series2.add(7.0, 2.0);
         series2.add(8.0, 1.0);

         final XYSeriesCollection dataset = new XYSeriesCollection();
         dataset.addSeries(series1);
         dataset.addSeries(series2);

        return dataset;
    }

    @Override
    public JPanel createPanel( String title ) {
      JFreeChart chart = createChart(createDataset( ), title );  
      
      ChartPanel chartPanel = new ChartPanel( chart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 350 ) );
      
      return chartPanel; 
   }

}




