import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

public class DegreeDistribution {
	int[] kc1;
	int[] kc2;
	double[] x;
	double[] y;
	ArrayList<Integer> xdata1;
	ArrayList<Integer> xdata2;
	ArrayList<Double> ydata1; 
	ArrayList<Double> ydata2; 
	int numNodes;
	String class1name;
	String class2name;
	DefaultCategoryDataset c1ds;
	DefaultCategoryDataset c2ds;
	KolmogorovSmirnovTest ksTest = new KolmogorovSmirnovTest();
	final double epsilon = 0.0001;
			
	public DegreeDistribution(Vector<EDDYNode> genes, String class1name, String class2name) {
		numNodes = genes.size();
		this.class1name = class1name;
		this.class2name = class2name;
		kc1 = new int[numNodes * (numNodes - 1) / 2];
		kc2 = new int[numNodes * (numNodes - 1) / 2];
		xdata1 = new ArrayList<Integer>();
		xdata2 = new ArrayList<Integer>();
		ydata1 = new ArrayList<Double>();
		ydata2 = new ArrayList<Double>();
		y = new double[genes.size()];
		x = new double[genes.size()];
		
		for (int i = 0; i < genes.size(); i++) {
			kc1[genes.get(i).edgesc1]++;
			kc2[genes.get(i).edgesc2]++;
			x[i] = genes.get(i).edgesc1;
			y[i] = genes.get(i).edgesc2;
			
			System.out.println(genes.get(i).name + " , " + genes.get(i).edgesc1 + " , " + genes.get(i).edgesc2);
		}
		for (int i = 0; i < genes.size(); i++) {
			System.out.print(genes.get(i).edgesc1);
		}
		System.out.println();
		for (int i = 0; i < genes.size(); i++) {
			System.out.print(genes.get(i).edgesc2);
		}
		System.out.println();

		calculateProbabilityDistributions();
		c1ds = new DefaultCategoryDataset();
		c2ds = new DefaultCategoryDataset();
		for (int i = 0; i < xdata1.size(); i++) {
			c1ds.addValue(ydata1.get(i), class1name.toUpperCase(), String.valueOf(i));
			c1ds.addValue(ydata2.get(i), class2name.toUpperCase(), String.valueOf(i));
		}
	}

	public void calculateProbabilityDistributions() {
		
		for (int i = 0; i < kc1.length; i++) {
			double y1 = kc1[i] / (numNodes + 0.0);
			double y2 = kc2[i] / (numNodes + 0.0);
			if(Math.abs(y1-0)>=epsilon || Math.abs(y2-0)>=epsilon){
				xdata1.add(i);
				ydata1.add(y1);
				xdata2.add(i);
				ydata2.add(y2);
			}
		}
		
	
		System.out.println();

	}

	public void graphProbabilityDistributions(double dia1, double dia2) {
		
		JFreeChart chartA = ChartFactory.createBarChart("Probability Degree Distributions", "Degree (k)", "Probability (Pk)", c1ds);
		chartA.getCategoryPlot().getRangeAxis().setRange(new Range(0,1));
		System.out.println(ydata1);
		System.out.println(ydata2);
		JFrame frame = new JFrame("Degree Distribution Graph");
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.add(new JScrollPane(new ChartPanel(chartA)));
		frame.add(new JLabel("KS Test: p= "+ksTestP()));
		frame.add(new JLabel("KS Statistic: D= "+ksStat()));
		frame.add(new JLabel(class1name.toUpperCase()+" diameter = "+dia1));
		frame.add(new JLabel(class2name.toUpperCase()+" diameter = "+dia2));
		frame.add(new JLabel("Diametric difference = "+Math.abs(dia1-dia2)));
		frame.pack();
		frame.setSize(750, 500);
		frame.setVisible(true);
	}
	
	public double ksTestP(){
		return ksTest.approximateP(ksStat(), x.length, y.length);
	}
	
	public double ksStat(){
		return ksTest.kolmogorovSmirnovStatistic(x,y);
	}
}
