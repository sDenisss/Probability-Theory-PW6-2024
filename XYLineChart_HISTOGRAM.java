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

public class XYLineChart_HISTOGRAM extends ApplicationFrame {

    public XYLineChart_HISTOGRAM(String applicationTitle, String chartTitle) {
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

        XYLineChart_HISTOGRAM chart = new XYLineChart_HISTOGRAM("Графики",
                "Гистограмма:");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    private XYDataset createDataset() {
        double[] sheduleArrX = Methods.getSheduleArrX();
        double[] sheduleArrY = Methods.getSheduleArrY();

        for (double d : sheduleArrX) {
            System.out.println("x  " + d);
        }

        for (double d : sheduleArrY) {
            System.out.println("y  " + d);
        }

        final XYSeries scheduleSeries = new XYSeries("F(x)");

        scheduleSeries.add(sheduleArrX[0], 0.0); // Начальная точка
        for (int i = 0; i < sheduleArrX.length; i++) {
            // Добавляем точку (sheduleArrX[i], sheduleArrY[j]) в зависимости от i
            int j = i / 2; // Определяем индекс Y в зависимости от i
            if (j >= sheduleArrY.length) {
                j = sheduleArrY.length - 1; // Ограничиваем j максимальным индексом
            }
            scheduleSeries.add(sheduleArrX[i], sheduleArrY[j]);
        }
        // Последняя точка для возврата к 0
        scheduleSeries.add(sheduleArrX[sheduleArrX.length - 1], 0.0);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(scheduleSeries);
        return dataset;
    }

}
