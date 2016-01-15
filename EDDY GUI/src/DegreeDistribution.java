import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.SimpleHistogramBin;
import org.jfree.data.statistics.SimpleHistogramDataset;

public class DegreeDistribution {
	int[] kc1;
	int[] kc2;
	int[] xdatac1;
	double[] ydatac1;
	int[] xdatac2;
	double[] ydatac2;
	int numNodes;
	String class1name;
	String class2name;
	SimpleHistogramDataset ds;
	SimpleHistogramDataset ds2;
	public DegreeDistribution(Vector<EDDYNode> genes, String class1name, String class2name) {
		numNodes = genes.size();
		this.class1name = class1name;
		this.class2name = class2name;
		kc1 = new int[numNodes * (numNodes - 1) / 2];
		kc2 = new int[numNodes * (numNodes - 1) / 2];
		xdatac1 = new int[(numNodes * (numNodes - 1)) / 2];
		ydatac1 = new double[(numNodes * (numNodes - 1)) / 2];
		xdatac2 = new int[numNodes * (numNodes - 1) / 2];
		ydatac2 = new double[numNodes * (numNodes - 1) / 2];
		
		ds = new SimpleHistogramDataset(class1name.toUpperCase());
		ds2 = new SimpleHistogramDataset(class2name.toUpperCase());
		ds.addBin(new SimpleHistogramBin(0, 0.5));
		ds2.addBin(new SimpleHistogramBin(0, 0.5));
		for (int i = 1; i < xdatac1.length; i++) {
			ds.addBin(new SimpleHistogramBin(i-0.49, i+0.5));
			ds2.addBin(new SimpleHistogramBin(i-0.49, i+0.5));
		}
		for (int i = 0; i < genes.size(); i++) {
			kc1[genes.get(i).edgesc1]++;
			kc2[genes.get(i).edgesc2]++;
			ds.addObservation(genes.get(i).edgesc1);
			ds2.addObservation(genes.get(i).edgesc2);
			System.out.println(genes.get(i).name + " , " + genes.get(i).edgesc1 + " , " + genes.get(i).edgesc2);
		}

		calculateProbabilityDistributions();
	}

	public void calculateProbabilityDistributions() {
		
		for (int i = 0; i < xdatac1.length; i++) {
			xdatac1[i] = 0;
			ydatac1[i] = 0.0;
			xdatac2[i] = 0;
			ydatac2[i] = 0.0;
		}
		for (int i = 0; i < xdatac1.length; i++) {
			xdatac1[i] = i;
			ydatac1[i] = kc1[i] / (numNodes + 0.0);
			xdatac2[i] = i;
			ydatac2[i] = kc2[i] / (numNodes + 0.0);
			System.out.print(ydatac1[i] + ", ");
		}
	}

	public void graphProbabilityDistributions() {
		
		JFreeChart chart = ChartFactory.createHistogram(class1name.toUpperCase()+" Degree Distribution", "K", "Instances", ds, PlotOrientation.VERTICAL, true,
				false, false);
		
		JFreeChart chart2 = ChartFactory.createHistogram(class2name.toUpperCase()+" Degree Distribution", "K", "Instances", ds2, PlotOrientation.VERTICAL, true,
				false, false);
		JFrame frame = new JFrame("Degree Distributions");
		
		ChartPanel chartA = new ChartPanel(chart);
		chartA.setPreferredSize(new Dimension(1000, 50));
		
		ChartPanel chartB = new ChartPanel(chart2);
		chartB.setPreferredSize(new Dimension(1000, 50));
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.add(new JScrollPane(chartA));
		frame.add(new JScrollPane(chartB));
		frame.pack();
		frame.setSize(750, 500);
		frame.setVisible(true);

	}
}
