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
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart extends Chart{

    public LineChart() {

    }

    @Override
    protected JFreeChart createChart( String title, String x, String y ) {
        
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
}




