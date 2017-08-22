package dimorfismes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import javafx.scene.paint.Paint;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class Grafic {
    JFreeChart grafic;
    XYSeriesCollection dades = new XYSeriesCollection();
    String titol;
    String etiqx;
    String etiqy;
    XYLineAndShapeRenderer renderer;
    ValueAxis linear;
    public Grafic(String t, String x, String y) {
        titol = t;
        etiqx = x;
        etiqy = y;
        grafic = ChartFactory.createXYLineChart(titol,x,y,dades, PlotOrientation.VERTICAL ,true,true,false);
        XYPlot plot = (XYPlot) grafic.getPlot();
        plot.getRangeAxis().setRange(-100, 100);
        renderer = new XYLineAndShapeRenderer();
        plot.setBackgroundPaint(Color.white);
        grafic.setBackgroundPaint(Color.decode("#f2f1f0"));
        linear = plot.getRangeAxis();
        //LogAxis logaxis = new LogAxis("P");
        //logaxis.setMinorTickMarksVisible(true);
        //plot.setRangeAxis(logaxis);
    }

    public Grafic(){
        this("S/N","x","y");
    
    }

    public void agregarGrafic(String id,double[] x, double[] y){
        XYSeries s = new XYSeries(id);
        int n = x.length;
        for(int i = 0; i < n; ++i){
            s.add(x[i],y[i]);
        }
        dades.addSeries(s);
    }
    
    public void agregarPunt(String id, double x, double y){
        XYSeries s = new XYSeries(id);
        s.add(x,y);
        dades.addSeries(s);
    }
    
    public void esRecta(int num){
        XYPlot plot = grafic.getXYPlot();
        renderer.setSeriesStroke(num,new BasicStroke(1.0f));
        renderer.setSeriesShapesVisible(num, false);
        plot.setRenderer(renderer);
    }
    public void esPunt(int num){
        XYPlot plot = grafic.getXYPlot();
        renderer.setSeriesStroke(num,new BasicStroke(4.0f));
        plot.setRenderer(renderer);
        
    }
    
    public void canviarRang(double x1, double x2, double y1, double y2){
        XYPlot plot = grafic.getXYPlot();
        plot.getDomainAxis().setRange(x1, x2);
        plot.getRangeAxis().setRange(y1, y2);
    }
    
    public void logy(){
        XYPlot plot = grafic.getXYPlot();
        LogarithmicAxis rangeAxis;
        rangeAxis = new LogarithmicAxis("Log(y)");
        rangeAxis.setAllowNegativesFlag(true);
        rangeAxis.setLog10TickLabelsFlag(true);
        plot.setRangeAxis(rangeAxis);
    }
    
    public void lin(){
        XYPlot plot = grafic.getXYPlot();
        plot.setRangeAxis(linear);
    }
    public void reset(){
        dades.removeAllSeries();
    }
     
    public boolean existeix(String id){
        return dades.getSeriesIndex(id) != -1;
    }
    public JPanel obteGrafic(){
        return new ChartPanel(grafic);
    }
}