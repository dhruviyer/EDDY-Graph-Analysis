import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import eddy.EasyReader;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SummaryTableGenerator {
	JFrame summaryFrame;
	JFXPanel panel;

	JFrame analysisFrame;
	JPanel analysisPanel;
	
	JMenuBar menubar;
	JMenu menu;
	
	OutputWriter ow;
	
	Vector<OutputWriter> ows;
	Vector<String> pathwayNames;
	
	OutputWriter selectedOW;
	int selectedIndex = 0;
	DecimalFormat df;
	
	public SummaryTableGenerator() {
		ows = new Vector<>();
		pathwayNames = new Vector<>();
		df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
	}

	private void initMenu() {
		JMenuItem dd = new JMenuItem("Degree Distribution Graphs");

		dd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DegreeDistribution pdd = new DegreeDistribution(selectedOW.genes, selectedOW.class1name,
						selectedOW.class2name);
				pdd.graphProbabilityDistributions(selectedOW.graphAnalysis.diameterClass1(), selectedOW.graphAnalysis.diameterClass2());
			}
		});

		JMenuItem bc = new JMenuItem("Betweeness Centrality");
		
		bc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setSize(500, 400);
				
				Vector<EDDYNode> btwSort = selectedOW.genes;
				btwSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(selectedOW.graphAnalysis.getNodeBtwClass1(o1.name)-selectedOW.graphAnalysis.getNodeBtwClass2(o1.name));
						double diff2 = Math.abs(selectedOW.graphAnalysis.getNodeBtwClass1(o2.name)-selectedOW.graphAnalysis.getNodeBtwClass2(o2.name));
						if(diff1>diff2){
							return -1;
						}else if(diff1<diff2){
							return 1;
						}else{
							return 0;
						}
					}
				});
				String[] columnNames = {"Name", selectedOW.class1name.toUpperCase(),selectedOW.class2name.toUpperCase(), "Difference"};
				String[][] rowData = new String[btwSort.size()][4];
				for(int row = 0; row<rowData.length;row++){
					double diff = Math.abs(selectedOW.graphAnalysis.getNodeBtwClass1(btwSort.get(row).name)-selectedOW.graphAnalysis.getNodeBtwClass2(btwSort.get(row).name));
					rowData[row][0] = btwSort.get(row).name;
					rowData[row][1] = ""+df.format(selectedOW.graphAnalysis.getNodeBtwClass1(btwSort.get(row).name));
					rowData[row][2] = ""+df.format(selectedOW.graphAnalysis.getNodeBtwClass2(btwSort.get(row).name));
					rowData[row][3] = ""+df.format(diff);
				}
				JTable table = new JTable(new DefaultTableModel(rowData, columnNames){
					@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					});
				
				table.setAutoCreateRowSorter(true);

				frame.setTitle("Betweeness Centrality");
				frame.getContentPane().add(new JScrollPane(table));
				frame.setVisible(true);
			}
		});
		
		JMenuItem dist = new JMenuItem("Average Distance");
		
		dist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setSize(500, 400);
				
				Vector<EDDYNode> distSort = selectedOW.genes;
				distSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(selectedOW.graphAnalysis.getAveDegreeClass1(o1.name)-selectedOW.graphAnalysis.getAveDegreeClass2(o1.name));
						double diff2 = Math.abs(selectedOW.graphAnalysis.getAveDegreeClass1(o2.name)-selectedOW.graphAnalysis.getAveDegreeClass2(o2.name));
						if(diff1>diff2){
							return -1;
						}else if(diff1<diff2){
							return 1;
						}else{
							return 0;
						}
					}
				});
				String[] columnNames = {"Name", selectedOW.class1name.toUpperCase(),selectedOW.class2name.toUpperCase(), "Difference"};
				String[][] rowData = new String[distSort.size()][4];
				for(int row = 0; row<rowData.length;row++){
					double diff = Math.abs(selectedOW.graphAnalysis.getAveDegreeClass1(distSort.get(row).name)-selectedOW.graphAnalysis.getAveDegreeClass2(distSort.get(row).name));
					rowData[row][0] = distSort.get(row).name;
					rowData[row][1] = ""+df.format(selectedOW.graphAnalysis.getNodeBtwClass1(distSort.get(row).name));
					rowData[row][2] = ""+df.format(selectedOW.graphAnalysis.getNodeBtwClass2(distSort.get(row).name));
					rowData[row][3] = ""+df.format(diff);
				}
				JTable table = new JTable(new DefaultTableModel(rowData, columnNames){
					@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					});
				
				table.setAutoCreateRowSorter(true);

				frame.setTitle("Average Node Distances");
				frame.getContentPane().add(new JScrollPane(table));
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

		JMenuItem cc = new JMenuItem("Closeness Centrality");
		
		cc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setSize(500, 400);
				
				Vector<EDDYNode> centSort = selectedOW.genes;
				centSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(selectedOW.graphAnalysis.clClass1.getVertexScore(o1.name)-selectedOW.graphAnalysis.clClass2.getVertexScore(o1.name));
						double diff2 = Math.abs(selectedOW.graphAnalysis.clClass1.getVertexScore(o2.name)-selectedOW.graphAnalysis.clClass2.getVertexScore(o2.name));
						if(diff1>diff2){
							return -1;
						}else if(diff1<diff2){
							return 1;
						}else{
							return 0;
						}
					}
				});
				
				String[] columnNames = {"Name", selectedOW.class1name.toUpperCase(),selectedOW.class2name.toUpperCase(), "Difference"};
				String[][] rowData = new String[centSort.size()][4];
				for(int row = 0; row<rowData.length;row++){
					double diff = Math.abs(selectedOW.graphAnalysis.clClass1.getVertexScore(centSort.get(row).name)-selectedOW.graphAnalysis.clClass2.getVertexScore(centSort.get(row).name));
					rowData[row][0] = centSort.get(row).name;
					rowData[row][1] = ""+df.format(selectedOW.graphAnalysis.clClass1.getVertexScore(centSort.get(row).name));
					rowData[row][2] = ""+df.format(selectedOW.graphAnalysis.clClass2.getVertexScore(centSort.get(row).name));
					rowData[row][3] = ""+df.format(diff);
				}
				JTable table = new JTable(new DefaultTableModel(rowData, columnNames){
					@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					});
				table.setAutoCreateRowSorter(true);

				frame.setTitle("Closeness Centrality");
				frame.getContentPane().add(new JScrollPane(table));
				frame.setVisible(true);
			}
		});
		
		JMenuItem clust = new JMenuItem("Clustering");
		
		clust.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setSize(500, 400);
			
				Vector<EDDYNode> clusterSort = selectedOW.genes;
				clusterSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(selectedOW.graphAnalysis.clusteringCoefficients1(o1.name)-selectedOW.graphAnalysis.clusteringCoefficients2(o1.name));
						double diff2 = Math.abs(selectedOW.graphAnalysis.clusteringCoefficients1(o2.name)-selectedOW.graphAnalysis.clusteringCoefficients2(o2.name));
						if(diff1>diff2){
							return -1;
						}else if(diff1<diff2){
							return 1;
						}else{
							return 0;
						}
					}
				});
				
				String[] columnNames = {"Name", selectedOW.class1name.toUpperCase(),selectedOW.class2name.toUpperCase(), "Difference"};
				String[][] rowData = new String[clusterSort.size()][4];
				for(int row = 0; row<rowData.length;row++){
					double diff = Math.abs(selectedOW.graphAnalysis.clusteringCoefficients1(clusterSort.get(row).name)-selectedOW.graphAnalysis.clusteringCoefficients2(clusterSort.get(row).name));
					rowData[row][0] = clusterSort.get(row).name;
					rowData[row][1] = ""+df.format(selectedOW.graphAnalysis.clusteringCoefficients1(clusterSort.get(row).name));
					rowData[row][2] = ""+df.format(selectedOW.graphAnalysis.clusteringCoefficients2(clusterSort.get(row).name));
					rowData[row][3] = ""+df.format(diff);
				}
				JTable table = new JTable(new DefaultTableModel(rowData, columnNames){
					@Override
						public boolean isCellEditable(int row, int column){
							return false;
						}
					});
				table.setAutoCreateRowSorter(true);

				frame.setTitle("Clustering");
				frame.getContentPane().add(new JScrollPane(table));
				frame.setVisible(true);
			}
		});
	
		JMenuItem eigen = new JMenuItem("Eigenvector Centrality");
		
		eigen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame = new JFrame();
				frame.setSize(500, 400);
				
				Vector<EDDYNode> eSort = selectedOW.genes;
				eSort.sort(new Comparator<EDDYNode>() {

					@Override
					public int compare(EDDYNode o1, EDDYNode o2) {
						double diff1 = Math.abs(selectedOW.graphAnalysis.getNodeEClass1(o1.name)-selectedOW.graphAnalysis.getNodeEClass2(o1.name));
						double diff2 = Math.abs(selectedOW.graphAnalysis.getNodeEClass1(o2.name)-selectedOW.graphAnalysis.getNodeEClass2(o2.name));
						if(diff1>diff2){
							return -1;
						}else if(diff1<diff2){
							return 1;
						}else{
							return 0;
						}
					}
				});
				
				String[] columnNames = {"Name", selectedOW.class1name.toUpperCase(),selectedOW.class2name.toUpperCase(), "Difference"};
				String[][] rowData = new String[eSort.size()][4];
				for(int row = 0; row<rowData.length;row++){
					double diff = Math.abs(selectedOW.graphAnalysis.getNodeEClass1(eSort.get(row).name)-selectedOW.graphAnalysis.getNodeEClass2(eSort.get(row).name));
					rowData[row][0] = eSort.get(row).name;
					rowData[row][1] = ""+df.format(selectedOW.graphAnalysis.getNodeEClass1(eSort.get(row).name));
					rowData[row][2] = ""+df.format(selectedOW.graphAnalysis.getNodeEClass2(eSort.get(row).name));
					rowData[row][3] = ""+df.format(diff);
				}
				JTable table = new JTable(new DefaultTableModel(rowData, columnNames){
				@Override
					public boolean isCellEditable(int row, int column){
						return false;
					}
				});
				table.setAutoCreateRowSorter(true);

				frame.setTitle("Eigenvector Centrality");
				frame.getContentPane().add(new JScrollPane(table));
				frame.setVisible(true);
			}
		});
		
		JMenu pathChooser = new JMenu("Pathways");
		ButtonGroup group = new ButtonGroup();
		
		JRadioButtonMenuItem[] options = new JRadioButtonMenuItem[pathwayNames.size()];
		for(int i = 0; i< options.length; i++){
			options[i] = new JRadioButtonMenuItem(pathwayNames.get(i));
			group.add(options[i]);
			
			options[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0; i< options.length; i++){
						if(e.getSource().equals(options[i]))
							selectedOW = ows.get(i);
					}
				}
			});
			if(i == 0){ 
				options[i].setSelected(true);
				selectedOW = ows.get(i);
			}
			pathChooser.add(options[i]);
		}
		
		menu.add(vn);
		menu.addSeparator();
		menu.add(dd);
		menu.addSeparator();
		menu.add(clust);
		menu.add(dist);
		menu.add(bc);
		menu.add(cc);
		menu.add(eigen);
		
		menubar.add(menu);
		menubar.add(pathChooser);
		
		
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
				
				ows.add(new OutputWriter(inputs[0] + "_EdgeList.txt", inputs[0] + "_NODEINFO.txt", inputFile,
						classInfoFile));
				output += "<tr><td>" + inputs[0] + "</td>" + "<td>" + inputs[2] + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[4])) + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[5])) + "</td></tr>";
				pathwayNames.add(inputs[0]);
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
		//ow = new OutputWriter(edgeList, nodeinfo, inputFile, classInfoFile);

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
				pathwayNames.add(inputs[0]);
				ows.add(new OutputWriter(edgeList, nodeinfo, inputFile,
						classInfoFile));
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
