/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import java.awt.Color;
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

public class LineChart extends ApplicationFrame {

   public LineChart( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
      /*JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Trésorie","Date",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
      */
      final JFreeChart lineChart = ChartFactory.createXYLineChart(
            "Line Chart Demo 6",      // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            createDataset(),                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
    lineChart.setBackgroundPaint(Color.white);
    
    // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        lineChart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final XYPlot plot = lineChart.getXYPlot();
      //  plot.setBackgroundPaint(Color.lightGray);
        plot.setBackgroundPaint(Color.white);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    //    renderer.setSeriesLinesVisible(0, false);
    //    renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
    /*    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
*/
        
        //Window rendering      
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 600 , 350 ) );
      setContentPane( chartPanel );
   }

   private XYSeriesCollection  createDataset( ) {
     /* DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      
     /* dataset.addValue( 15 , "1" , "1970" );
      dataset.addValue( 30 , "1" , "" );
      dataset.addValue( 60 , "1" ,  "1990" );
      dataset.addValue( 120 , "1" , "" );
      dataset.addValue( 240 , "1" , "2010" );
      dataset.addValue( 300 , "1" , "2014" );
      */
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

}




