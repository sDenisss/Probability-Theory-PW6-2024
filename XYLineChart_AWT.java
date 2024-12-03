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

public class XYLineChart_AWT extends ApplicationFrame {

   public XYLineChart_AWT(String applicationTitle, String chartTitle) {
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
      Methods.statisticalCaracteristics(numbers); // Заполняет sheduleArrX и sheduleArrY

      XYLineChart_POLYGON chart = new XYLineChart_POLYGON("Графики",
            "График функции F(x):");
      chart.pack();
      RefineryUtilities.centerFrameOnScreen(chart);
      chart.setVisible(true);
   }

   private XYDataset createDataset() {
      // Получаем массивы sheduleArrX и sheduleArrY из класса Methods
      double[] sheduleArrX = Methods.getSheduleArrX();
      double[] sheduleArrY = Methods.getSheduleArrY();

      final XYSeries scheduleSeries = new XYSeries("F(x)");
      final XYSeries mark = new XYSeries("Mark");

      mark.add(0.0, 0.0);
      mark.add(0.0, 1.0);

      // Заполняем данные для графика
      for (int i = 0; i < sheduleArrX.length; i++) {
         scheduleSeries.add(sheduleArrX[i], sheduleArrY[i]);
         if (i != sheduleArrX.length - 1) {
            scheduleSeries.add(sheduleArrX[i], sheduleArrY[i + 1]);
         }

      }

      final XYSeriesCollection dataset = new XYSeriesCollection();
      dataset.addSeries(scheduleSeries);
      dataset.addSeries(mark);
      return dataset;
   }

}
