package in.co.vertex.feedback.j2p;

import java.awt.Color;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.opensymphony.xwork2.ActionSupport;

public class GetChartAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;

	public String execute() throws Exception {
		final PieDataset dataset = createDataset(14);

		// PiePlot3D piePlot3D = new PiePlot3D(dataset);

		chart = ChartFactory.createPieChart("Pie Chart Demo 4", // chart title
				dataset, // dataset
				false, // include legend
				true, false);

		// set the background color for the chart...
		chart.setBackgroundPaint(new Color(222, 222, 255));
		final PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setCircular(true);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat
						.getPercentInstance()));
		plot.setNoDataMessage("No data available");

		// add the chart to a panel...
		// final ChartPanel chartPanel = new ChartPanel(chart);
		// chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// setContentPane(chartPanel);

		// final Rotator rotator = new Rotator(plot);
		// rotator.start();

		// ValueAxis xAxis = new NumberAxis("Input Increase");
		// ValueAxis yAxis = new NumberAxis("Production");
		// XYSeries xySeries = new XYSeries(new Integer(1));
		// xySeries.add(0, 200);
		// xySeries.add(1, 300);
		// xySeries.add(2, 500);
		// xySeries.add(3, 700);
		// xySeries.add(4, 700);
		// xySeries.add(5, 900);
		// XYSeriesCollection xyDataset = new XYSeriesCollection(xySeries);
		//
		// // create XYPlot
		//
		// XYPlot xyPlot = new XYPlot(xyDataset, xAxis, yAxis,
		// new StandardXYItemRenderer(
		// StandardXYItemRenderer.SHAPES_AND_LINES));
		//
		// chart = new JFreeChart(xyPlot);

//		chart = new JFreeChart(plot);
		return SUCCESS;
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @param sections
	 *            the number of sections.
	 * 
	 * @return A sample dataset.
	 */
	private PieDataset createDataset(final int sections) {
		final DefaultPieDataset result = new DefaultPieDataset();
		for (int i = 0; i < sections; i++) {
			final double value = 100.0 * Math.random();
			result.setValue("Section " + i, value);
		}
		return result;
	}

	public JFreeChart getChart() {
		return chart;
	}
}