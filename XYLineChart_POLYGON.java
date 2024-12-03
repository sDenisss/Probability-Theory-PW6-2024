import java.awt.Color;
import java.awt.BasicStroke;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYLineChart_POLYGON extends ApplicationFrame {

   public XYLineChart_POLYGON(String applicationTitle, String chartTitle) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
            chartTitle,
            "X",
            "F(X)",
            createDataset(),
            PlotOrientation.VERTICAL,
            true, true, false);

      ChartPanel chartPanel = new ChartPanel(xylineChart);
      chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
      final XYPlot plot = xylineChart.getXYPlot();

      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      renderer.setSeriesPaint(0, Color.RED);
      renderer.setSeriesPaint(1, Color.BLACK);
      renderer.setSeriesStroke(0, new BasicStroke(2.0f));
      renderer.setSeriesStroke(1, new BasicStroke(4.0f));
      plot.setRenderer(renderer);
      setContentPane(chartPanel);
   }

   public static void main(String[] args) {
      // Пример вызова методов для заполнения массивов sheduleArrX и sheduleArrY
      double[] numbers = Main.getNumbers(); // Пример данных
      Methods.findIntervalStatisticalRange(numbers, numbers.length); // Заполняет sheduleArrX и sheduleArrY

      XYLineChart_POLYGON chart = new XYLineChart_POLYGON("Графики",
            "Полигон приведенных частот группированной выборки:");
      chart.pack();
      RefineryUtilities.centerFrameOnScreen(chart);
      chart.setVisible(true);
   }

   private XYDataset createDataset() {
      double[] sheduleArrX = Methods.getSheduleArrX();
      double[] sheduleArrY = Methods.getSheduleArrY();

      double[] polygonArrX = new double[sheduleArrX.length / 2];

      double difference = sheduleArrX[sheduleArrX.length - 1] - sheduleArrX[sheduleArrX.length - 2];

      for (int i = polygonArrX.length - 1; i >= 0; i--) {
         if (i == polygonArrX.length - 1) {
            polygonArrX[i] = sheduleArrX[sheduleArrX.length - 1] - (difference / 2);

         } else {
            polygonArrX[i] = polygonArrX[i + 1] - difference;
         }

         System.out.println(polygonArrX[i]);
      }

      // for (double d : sheduleArrX) {
      // System.out.println("x " + d);
      // }

      // for (double d : sheduleArrY) {
      // System.out.println("y " + d);
      // }

      final XYSeries scheduleSeries = new XYSeries("F(x)");

      for (int i = 0; i < polygonArrX.length; i++) {

         scheduleSeries.add(polygonArrX[i], sheduleArrY[i]);
      }

      final XYSeriesCollection dataset = new XYSeriesCollection();
      dataset.addSeries(scheduleSeries);
      return dataset;
   }

}
