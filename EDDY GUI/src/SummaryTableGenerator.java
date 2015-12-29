import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	
	OutputWriter ow;
	public SummaryTableGenerator(){
		
		
	}
	
	private void initAnalysisPanel() {
		JButton jb = new JButton("Generate PDD Graphs");
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProbabilityDegreeDistribution pdd = new ProbabilityDegreeDistribution(ow.genes,ow.class1name,ow.class2name);
				pdd.graphProbabilityDistributions();
			}
		});
		
		JButton vn = new JButton("Visulize Networks");
		
		vn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EasyReader reader = new EasyReader("links.txt");
				String line = reader.readLine();
				while(line != null){
					try {
						Desktop.getDesktop().browse(new URI(("file://"+line).replace(" ", "%20")));
						line = reader.readLine();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		analysisPanel.add(jb);
		analysisPanel.add(vn);
	}

	public void makeTable(String inputFile, String classInfoFile){
		try {
		    BufferedReader reader = new BufferedReader(new FileReader("eddy.gmt.output.txt"));
			for (int counter = 0; counter < 10; counter++) {
				reader.readLine();
			}
			String output = "<!DOCTYPE HTML><html><head><script type=\"text/javascript\" src=\"sorttable.js\"></script></head><body>";
			output += "<table class=\"sortable\" border=\"1\" cellpadding=\"15\">" + "<tr>"
					+ "<th>Name</th>" + "<th>Size</th>" + "<th>JS Divergence</th>"
					+ "<th>P-Value</th></tr>";
			
			
			String line = reader.readLine();

			String[] inputs = null;
			while (!(line.equals(""))) {
				inputs = line.split("\t");
				ow = new OutputWriter(inputs[0]+"_EdgeList.txt", inputs[0]+"_NODEINFO.txt", inputFile, classInfoFile);
				output += "<tr><td>"+inputs[0]+"</td>" + "<td>" + inputs[2] + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[4]))
						+ "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[5]))
						+ "</td></tr>";
				line = reader.readLine();
			
				//start necessary data operations
			}
			output += "</table></body></html>";
			PrintWriter writer = new PrintWriter("index.html", "UTF-8");
			writer.write(output);
			writer.close();
			
			summaryFrame = new JFrame("Evaluation of Dependency Differentiality");
			panel = new JFXPanel();
			summaryFrame.setSize(775,800);
			summaryFrame.add(panel);
			summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			summaryFrame.setResizable(true);
			summaryFrame.setLocationRelativeTo(null);
			summaryFrame.setVisible(true);
			
			analysisFrame = new JFrame("Evaluation of Dependency Differentiality");
			analysisPanel = new JPanel();
			initAnalysisPanel();
			analysisFrame.setSize(300,200);
			analysisFrame.add(analysisPanel);
			analysisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			analysisFrame.setResizable(true);
			analysisFrame.setLocationRelativeTo(summaryFrame);
			analysisFrame.setVisible(true);
			
			
			
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
	
	public void makeTable(String edgeList, String nodeinfo, String inputFile, String classInfoFile){
		ow = new OutputWriter(edgeList, nodeinfo, inputFile, classInfoFile);
		
		try {
			JOptionPane.showMessageDialog(null, "You will now select the path of the output \nfile from your EDDY run. Typically, \nthis file is called \"eddy.gmt.output.txt\"");
			JFileChooser jfc = new JFileChooser();
			int result = jfc.showOpenDialog(null);
			EasyReader reader = null;
			if(result == JFileChooser.APPROVE_OPTION){
				reader = new EasyReader(jfc.getSelectedFile().getAbsolutePath());
			
		
			String output = "<!DOCTYPE HTML><html><head><script type=\"text/javascript\" src=\"sorttable.js\"></script></head><body>";
			output += "<table class=\"sortable\" border=\"1\" cellpadding=\"15\">" + "<tr>"
					+ "<th>Name</th>" + "<th>Size</th>" + "<th>JS Divergence</th>"
					+ "<th>P-Value</th></tr>";
			
			for(int i = 0; i<10;i++) reader.readLine();
			
			String line = reader.readLine();
			String[] inputs = null;
			
				inputs = line.split("\t");
				
				output += "<tr><td>"+inputs[0]+"</td>" + "<td>" + inputs[2] + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[4]))
						+ "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[5]))
						+ "</td></tr>";
			
			output += "</table></body></html>";
			PrintWriter writer = new PrintWriter("index.html", "UTF-8");
			writer.write(output);
			writer.close();
			summaryFrame = new JFrame("Evaluation of Dependency Differentiality");
			panel = new JFXPanel();
			summaryFrame.setSize(775,800);
			summaryFrame.add(panel);
			summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			summaryFrame.setResizable(true);
			summaryFrame.setLocationRelativeTo(null);
			summaryFrame.setVisible(true);
			
			analysisFrame = new JFrame("Evaluation of Dependency Differentiality");
			analysisPanel = new JPanel();
			initAnalysisPanel();
			analysisFrame.setSize(300,200);
			analysisFrame.add(analysisPanel);
			analysisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			analysisFrame.setResizable(true);
			analysisFrame.setLocationRelativeTo(summaryFrame);
			analysisFrame.setVisible(true);
			
			
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					initFX(panel);
				}
			});
			
			}else{
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
		//webEngine.load("http:\\google.com");
		webEngine.load("file:///" + new File("index.html").getAbsolutePath());

		root.getChildren().add(browser);

		return (scene);
	}
}
