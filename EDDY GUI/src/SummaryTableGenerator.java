import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.swing.JFrame;

import eddy.EasyReader;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class SummaryTableGenerator {
	JFrame frame;
	JFXPanel panel;
	public SummaryTableGenerator(){
		frame = new JFrame("Evaluation of Dependency Differentiality");
		panel = new JFXPanel();
		frame.setSize(775,800);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(panel);
			}
		});
		
	}
	
	public void makeTable(String inputFile, String classInfoFile){
		try {
		    BufferedReader reader = new BufferedReader(new FileReader("eddy.gmt.output.txt"));
			EasyReader linksReader = new EasyReader("links.txt");
			for (int counter = 0; counter < 10; counter++) {
				reader.readLine();
			}
			String output = "<!DOCTYPE HTML><html><head><script type=\"text/javascript\" src=\"sorttable.js\"></script></head><body>";
			output += "<table class=\"sortable\" border=\"1\" cellpadding=\"15\">" + "<tr>"
					+ "<th>Name</th>" + "<th>Size</th>" + "<th>JS Divergence</th>"
					+ "<th>P-Value</th></tr>";
			

			String line = reader.readLine();
			String link = linksReader.readLine();
			String[] inputs = null;
			while (!(line.equals(""))) {
				inputs = line.split("\t");
				//new OutputWriter(inputs[0]+"_EdgeList.txt", inputs[0]+"_NODEINFO.txt", inputFile, classInfoFile);
				output += "<tr><td>"+inputs[0]+"</td>" + "<td>" + inputs[2] + "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[4]))
						+ "</td>" + "<td>"
						+ new DecimalFormat("#0.00000").format(Float.parseFloat(inputs[5]))
						+ "</td></tr>";
				line = reader.readLine();
				link = linksReader.readLine();

			}
			output += "</table></body></html>";
			PrintWriter writer = new PrintWriter("index.html", "UTF-8");
			writer.write(output);
			writer.close();
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
