import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eddy.EasyReader;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;

public class SummaryTableGenerator {
	JFrame summaryFrame;
	JFXPanel panel;

	JFrame analysisFrame;
	JPanel analysisPanel;
	
	JMenuBar menubar;
	JMenu menu;
	
	OutputWriter ow;

	public SummaryTableGenerator() {

	}

	private void initMenu() {
		JMenuItem dd = new JMenuItem("Degree Distribution Graphs");

		dd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DegreeDistribution pdd = new DegreeDistribution(ow.genes, ow.class1name,
						ow.class2name);
				pdd.graphProbabilityDistributions();

			}
		});

		JMenuItem bc = new JMenuItem("Betweeness Centrality Difference Charts");
		
		bc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JTextArea list = new JTextArea();
				list.setEditable(false);
				frame.setSize(500, 400);
				list.append("Genes betweeness centrality difference (ranked highest to lowest):\n");
				
				Vector<EDDYNode> btwSort = ow.genes;
				btwSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(ow.graphAnalysis.getNodeBtwClass1(o1.name)-ow.graphAnalysis.getNodeBtwClass2(o1.name));
						double diff2 = Math.abs(ow.graphAnalysis.getNodeBtwClass1(o2.name)-ow.graphAnalysis.getNodeBtwClass2(o2.name));
						if(diff1>diff2){
							return -1;
						}else if(diff1<diff2){
							return 1;
						}else{
							return 0;
						}
					}
				});
				
				for(int i = 0; i<btwSort.size();i++){
					double diff = Math.abs(ow.graphAnalysis.getNodeBtwClass1(btwSort.get(i).name)-ow.graphAnalysis.getNodeBtwClass2(btwSort.get(i).name));
					list.append(btwSort.get(i).name+":\t"+diff+"\n");
				}
				frame.getContentPane().add(new JScrollPane(list));
				frame.setVisible(true);
			}
		});
		
		JMenuItem vn = new JMenuItem("Visulize Networks");

		vn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EasyReader reader = new EasyReader("links.txt");
				String line = reader.readLine();
				while (line != null) {
					try {
						Desktop.getDesktop().browse(new URI(("file://" + line).replace(" ", "%20")));
						line = reader.readLine();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		JMenuItem cc = new JMenuItem("Closeness Centrality Difference Charts");
		
		cc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JTextArea list = new JTextArea();
				list.setEditable(false);
				frame.setSize(500, 400);
				list.append("Genes closeness centrality difference (ranked highest to lowest):\n");
				
				Vector<EDDYNode> centSort = ow.genes;
				centSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(ow.graphAnalysis.clClass1.getVertexScore(o1.name)-ow.graphAnalysis.clClass2.getVertexScore(o1.name));
						double diff2 = Math.abs(ow.graphAnalysis.clClass1.getVertexScore(o2.name)-ow.graphAnalysis.clClass2.getVertexScore(o2.name));
						if(diff1>diff2){
							return -1;
						}else if(diff1<diff2){
							return 1;
						}else{
							return 0;
						}
					}
				});
				
				for(int i = 0; i<centSort.size();i++){
					double diff = Math.abs(ow.graphAnalysis.clClass1.getVertexScore(centSort.get(i).name)-ow.graphAnalysis.clClass2.getVertexScore(centSort.get(i).name));
					list.append(centSort.get(i).name+":\t"+diff+"\n");
				}
				frame.getContentPane().add(new JScrollPane(list));
				frame.setVisible(true);
			}
		});
		
		JMenuItem clust = new JMenuItem("Clustering Difference Charts");
		
		clust.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				JTextArea list = new JTextArea();
				list.setEditable(false);
				frame.setSize(500, 400);
				list.append("Genes cluster coefficient difference (ranked highest to lowest):\n");
				
				Vector<EDDYNode> clusterSort = ow.genes;
				clusterSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(ow.graphAnalysis.clusteringCoefficients1(o1.name)-ow.graphAnalysis.clusteringCoefficients2(o1.name));
						double diff2 = Math.abs(ow.graphAnalysis.clusteringCoefficients1(o1.name)-ow.graphAnalysis.clusteringCoefficients2(o1.name));
						if(diff1>diff2){
							return 1;
						}else if(diff1<diff2){
							return -1;
						}else{
							return 0;
						}
					}
				});
				
				for(int i = 0; i<clusterSort.size();i++){
					double diff = Math.abs(ow.graphAnalysis.clusteringCoefficients1(ow.genes.get(i).name)-ow.graphAnalysis.clusteringCoefficients2(ow.genes.get(i).name));
					list.append(clusterSort.get(i).name+":\t"+diff+"\n");
				}
				frame.getContentPane().add(new JScrollPane(list));
				frame.setVisible(true);
			}
		});
	
		/*analysisPanel.add(vn);
		analysisPanel.add(dd);
		analysisPanel.add(bc);
		analysisPanel.add(cc);
		analysisPanel.add(clust);*/
		menu.add(vn);
		menu.addSeparator();
		menu.add(dd);
		menu.add(bc);
		menu.add(cc);
		menu.add(clust);
		menubar.add(menu);
		
		
	}

	public void makeTable(String inputFile, String classInfoFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("eddy.gmt.output.txt"));
			for (int counter = 0; counter < 10; counter++) {
				reader.readLine();
			}
			String output = "<!DOCTYPE HTML><html><head><script type=\"text/javascript\" src=\"sorttable.js\"></script></head><body>";
			output += "<table class=\"sortable\" border=\"1\" cellpadding=\"15\">" + "<tr>" + "<th>Name</th>"
					+ "<th>Size</th>" + "<th>JS Divergence</th>" + "<th>P-Value</th></tr>";

			String line = reader.readLine();

			String[] inputs = null;
			while (!(line.equals(""))) {
				inputs = line.split("\t");
				ow = new OutputWriter(inputs[0] + "_EdgeList.txt", inputs[0] + "_NODEINFO.txt", inputFile,
						classInfoFile);
				output += "<tr><td>" + inputs[0] + "</td>" + "<td>" + inputs[2] + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[4])) + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[5])) + "</td></tr>";
				line = reader.readLine();

				// start necessary data operations
			}
			output += "</table></body></html>";
			PrintWriter writer = new PrintWriter("index.html", "UTF-8");
			writer.write(output);
			writer.close();

			summaryFrame = new JFrame("EDDY: Evaluation of Differential DependencY");
				panel = new JFXPanel();
				summaryFrame.setSize(775, 800);
				summaryFrame.add(panel);
				summaryFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				summaryFrame.addWindowListener(new WindowAdapter() {

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosing(WindowEvent e) {
						int confirm = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
								"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
								null);
						if (confirm == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}
				});
				summaryFrame.setResizable(true);
				summaryFrame.setLocationRelativeTo(null);
				menubar = new JMenuBar();
				menu = new JMenu("Analysis");
				initMenu();
				summaryFrame.setJMenuBar(menubar);
				summaryFrame.setVisible(true);
				/*
				analysisFrame = new JFrame("EDDY: Evaluation of Differential DependencY");
				analysisPanel = new JPanel();
				analysisPanel.setLayout(new GridLayout(5, 1));
				initAnalysisPanel();
				analysisFrame.setSize(300, 200);
				analysisFrame.add(analysisPanel);
				analysisFrame.setResizable(true);
				analysisFrame.setLocationRelativeTo(summaryFrame);
				analysisFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				//analysisFrame.setVisible(true);
				*/
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					initFX(panel);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void makeTable(String edgeList, String nodeinfo, String inputFile, String classInfoFile) {
		ow = new OutputWriter(edgeList, nodeinfo, inputFile, classInfoFile);

		try {
			JOptionPane.showMessageDialog(null,
					"You will now select the path of the output \nfile from your EDDY run. Typically, \nthis file is called \"eddy.gmt.output.txt\"");
			JFileChooser jfc = new JFileChooser();
			int result = jfc.showOpenDialog(null);
			EasyReader reader = null;
			if (result == JFileChooser.APPROVE_OPTION) {
				reader = new EasyReader(jfc.getSelectedFile().getAbsolutePath());

				String output = "<!DOCTYPE HTML><html><head><script type=\"text/javascript\" src=\"sorttable.js\"></script></head><body>";
				output += "<table class=\"sortable\" border=\"1\" cellpadding=\"15\">" + "<tr>" + "<th>Name</th>"
						+ "<th>Size</th>" + "<th>JS Divergence</th>" + "<th>P-Value</th></tr>";

				for (int i = 0; i < 10; i++)
					reader.readLine();

				String line = reader.readLine();
				String[] inputs = null;

				inputs = line.split("\t");

				output += "<tr><td>" + inputs[0] + "</td>" + "<td>" + inputs[2] + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[4])) + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[5])) + "</td></tr>";

				output += "</table></body></html>";
				PrintWriter writer = new PrintWriter("index.html", "UTF-8");
				writer.write(output);
				writer.close();
				summaryFrame = new JFrame("EDDY: Evaluation of Differential DependencY");
				panel = new JFXPanel();
				summaryFrame.setSize(775, 800);
				summaryFrame.add(panel);
				summaryFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				summaryFrame.addWindowListener(new WindowAdapter() {

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosing(WindowEvent e) {
						int confirm = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
								"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
								null);
						if (confirm == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}
				});
				summaryFrame.setResizable(true);
				summaryFrame.setLocationRelativeTo(null);
				menubar = new JMenuBar();
				menu = new JMenu("Analysis");
				initMenu();
				summaryFrame.setJMenuBar(menubar);
				summaryFrame.setVisible(true);
				/*
				analysisFrame = new JFrame("EDDY: Evaluation of Differential DependencY");
				analysisPanel = new JPanel();
				analysisPanel.setLayout(new GridLayout(5, 1));
				initAnalysisPanel();
				analysisFrame.setSize(300, 200);
				analysisFrame.add(analysisPanel);
				analysisFrame.setResizable(true);
				analysisFrame.setLocationRelativeTo(summaryFrame);
				analysisFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				//analysisFrame.setVisible(true);
				*/

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						initFX(panel);
					}
				});

			} else {
				JOptionPane.showMessageDialog(null, "Cannot proceed without output file. Exiting.");
				System.exit(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	private static void initFX(JFXPanel fxPanel) {
		// This method is invoked on the JavaFX thread, create the scene to hold
		// all of the elements
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}

	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root, 1000, 1000);

		// Webview used to display JS
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		// String myURL = "file:///"+index.getAbsolutePath(); //the source file
		// is set from witin the CytoscapeSwing class
		// webEngine.load("http:\\google.com");
		webEngine.load("file:///" + new File("index.html").getAbsolutePath());

		root.getChildren().add(browser);

		return (scene);
	}
}
