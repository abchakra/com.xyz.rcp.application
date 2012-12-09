package in.co.vertex.feedback.j2p;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.TableOrder;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetChartForBatchIdAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8739266377747753777L;
	private JFreeChart chart;

	public JFreeChart getChart() {
		return chart;
	}

	/**
	 * Creates a sample chart with the given dataset.
	 * 
	 * @param dataset
	 *            the dataset.
	 * 
	 * @return A sample chart.
	 */
	private JFreeChart createChart(final CategoryDataset dataset) {
//		final JFreeChart chart = ChartFactory.createMultiplePieChart(
//				"Batch Report Pie Chart", // chart title
//				dataset, // dataset
//				TableOrder.BY_ROW, true, // include legend
//				true, false);
//		final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
//		plot.setBackgroundPaint(Color.white);
//		plot.setOutlineStroke(new BasicStroke(1.0f));
//		final JFreeChart subchart = plot.getPieChart();
//		final PiePlot p = (PiePlot) subchart.getPlot();
//		// p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
//
//		p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})",
//				NumberFormat.getNumberInstance(), NumberFormat
//						.getPercentInstance()));
//
//		// p.setMaximumLabelWidth(0.35);
//		p.setLabelFont(new Font("SansSerif", Font.PLAIN, 8));
//		p.setInteriorGap(0.30);
//
//		return chart;

		 final JFreeChart chart = ChartFactory.createMultiplePieChart(
		 "Multiple Pie Chart", // chart title
		 dataset, // dataset
		 TableOrder.BY_ROW, true, // include legend
		 true, false);
		 final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
		 plot.setBackgroundPaint(Color.white);
		 plot.setOutlineStroke(new BasicStroke(1.0f));
		 final JFreeChart subchart = plot.getPieChart();
		 final PiePlot p = (PiePlot) subchart.getPlot();
		 p.setBackgroundPaint(null);
		 p.setOutlineStroke(null);
		 p.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})",
		 NumberFormat.getNumberInstance(), NumberFormat
		 .getPercentInstance()));
//		 p.setMaximumLabelWidth(0.35);
		 p.setLabelFont(new Font("SansSerif", Font.PLAIN, 9));
		 p.setInteriorGap(0.30);
		 return chart;

	}

	private JFreeChart createChart2() {
		JPanel panel = new JPanel(new GridLayout(2, 2));

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Section 1", 23.3);
		dataset.setValue("Section 2", 56.5);
		dataset.setValue("Section 3", 43.3);
		dataset.setValue("Section 4", 11.1);

		JFreeChart chart1 = ChartFactory.createPieChart("Chart 1", dataset,
				false, false, false);
		JFreeChart chart2 = ChartFactory.createPieChart("Chart 2", dataset,
				false, false, false);
		PiePlot plot2 = (PiePlot) chart2.getPlot();
		plot2.setCircular(false);
		JFreeChart chart3 = ChartFactory.createPieChart3D("Chart 3", dataset,
				false, false, false);
		PiePlot3D plot3 = (PiePlot3D) chart3.getPlot();
		plot3.setForegroundAlpha(0.6f);
		plot3.setCircular(true);
		JFreeChart chart4 = ChartFactory.createPieChart3D("Chart 4", dataset,
				false, false, false);
		PiePlot3D plot4 = (PiePlot3D) chart4.getPlot();
		plot4.setForegroundAlpha(0.6f);

		panel.add(new ChartPanel(chart1));
		panel.add(new ChartPanel(chart2));
		panel.add(new ChartPanel(chart3));
		panel.add(new ChartPanel(chart4));

		return chart;

	}

	public String execute() throws Exception {

		Map session = ActionContext.getContext().getSession();
		final CategoryDataset dataset = (CategoryDataset) session
				.get("dataset");

		chart = createChart(dataset);

		// final ChartPanel chartPanel = new ChartPanel(chart, true, true, true,
		// false, true);
		// chartPanel.setPreferredSize(new java.awt.Dimension(600, 380));
		// setContentPane(chartPanel);

		return SUCCESS;
	}
}
