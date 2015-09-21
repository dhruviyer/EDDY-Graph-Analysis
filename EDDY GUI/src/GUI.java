import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GUI {

	// arguments to EDDY
	static JButton inputDataFileSelector;
	static JButton geneSetFileSelector;
	static JButton classInfoFileSelector;
	static JCheckBox edf;
	static JFormattedTextField minGeneSetSize;
	static JFormattedTextField maxGeneSetSize;
	static JFormattedTextField numThreads;
	static JFormattedTextField pVal;
	static JRadioButton quickPermEnabled;
	static JFormattedTextField numNetStruc;
	static JFormattedTextField numPerms;
	static JButton runEDDY;
	static JTextArea output;
	static JCheckBox showAdvancedOptions;
	static JFormattedTextField resamplingRate;
	static JCheckBox priorDirectionality;
	static JCheckBox includeNeighbor;
	static JRadioButton approximatePermuations;
	static JFormattedTextField priorWeight;
	static JButton resetFields;
	
	// various labels
	static JLabel lbl0;
	static JLabel lbl1;
	static JLabel lbl2;
	static JLabel lbl3;
	static JLabel lbl4;
	static JLabel lbl5;
	static JLabel lbl6;
	static JLabel lbl7;
	static JLabel lbl8;
	static JLabel lbl9;
	static JLabel lbl10;
	static JLabel lbl11;
	static JLabel lbl12;
	static JLabel lbl13;
	static JLabel lbl14;
	static JLabel lbl15;
	static JLabel lbl16;
	
	static JTabbedPane tabbedPane;
	static JPanel settingsPanel;
	static JFXPanel cytoscapePanel;

	static JFrame frame;

	static SpringLayout sp;
	
	static final int VERT_SPACING = 25; //vertical spacing for layout
	static final int HORIZ_SPACINGC1 = 15; //horizontal spacing for column 1 of layout
	static final int HORIZ_SPACINGC2 = 250; //horizontal spacing for column 2 in layout
	static final int HORIZ_SPACINGC3 = 530; //horizontal spacing for column 3 of layout
	static final int HORIZ_SPACINGC4 = 765; //horizontal spacing for column 3 of layout
	
	static int settingsFrameHeight = 800;
	static int settingsFrameWidth = 545;
	
	
	public static void main(String[] args) {
		init(); // initialize elements
		add(); // add elements to panels and frames
		setListeners(); //add listeners
		setDefaults(); //set any and all default values
		show(); // display
	}

	private static void setDefaults() {
		minGeneSetSize.setText("-1");
		maxGeneSetSize.setText("-1");
		pVal.setText("0.05");
		numNetStruc.setText("-1");
		numPerms.setText("1000");
		resamplingRate.setText("0.8");
		priorDirectionality.setSelected(false);
		includeNeighbor.setSelected(false);
		approximatePermuations.setSelected(true);
		priorWeight.setText("0.0");
		edf.setSelected(true);
		quickPermEnabled.setSelected(false);
	}

	private static void setListeners() {
		showAdvancedOptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!showAdvancedOptions.isSelected()){
					settingsFrameHeight = 800;
					settingsFrameWidth = 550;
					frame.setSize(settingsFrameWidth,settingsFrameHeight);
					
					edf.setVisible(false);
					minGeneSetSize.setVisible(false);
					maxGeneSetSize.setVisible(false);
					numThreads.setVisible(false);
					pVal.setVisible(false);
					quickPermEnabled.setVisible(false);
					numNetStruc.setVisible(false);
					numPerms.setVisible(false);
					resamplingRate.setVisible(false);
					priorDirectionality.setVisible(false);
					includeNeighbor.setVisible(false);
					approximatePermuations.setVisible(false);
					priorWeight.setVisible(false);
					
					lbl4.setVisible(false);
					lbl5.setVisible(false);
					lbl6.setVisible(false);
					lbl7.setVisible(false);
					lbl8.setVisible(false);
					lbl9.setVisible(false);
					lbl10.setVisible(false);
					lbl11.setVisible(false);
					lbl12.setVisible(false);
					lbl13.setVisible(false);
					lbl14.setVisible(false);
					lbl15.setVisible(false);
					lbl16.setVisible(false);
					
				}else{
					settingsFrameHeight = 800;
					settingsFrameWidth = 1025;
					frame.setSize(settingsFrameWidth,settingsFrameHeight);
					
					edf.setVisible(true);
					minGeneSetSize.setVisible(true);
					maxGeneSetSize.setVisible(true);
					numThreads.setVisible(true);
					pVal.setVisible(true);
					quickPermEnabled.setVisible(true);
					numNetStruc.setVisible(true);
					numPerms.setVisible(true);
					resamplingRate.setVisible(true);
					priorDirectionality.setVisible(true);
					includeNeighbor.setVisible(true);
					approximatePermuations.setVisible(true);
					priorWeight.setVisible(true);
					
					lbl4.setVisible(true);
					lbl5.setVisible(true);
					lbl6.setVisible(true);
					lbl7.setVisible(true);
					lbl8.setVisible(true);
					lbl9.setVisible(true);
					lbl10.setVisible(true);
					lbl11.setVisible(true);
					lbl12.setVisible(true);
					lbl13.setVisible(true);
					lbl14.setVisible(true);
					lbl16.setVisible(true);
					lbl15.setVisible(true);
				}
				
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 1){
					frame.setSize(1025, 800);
				}else{
					frame.setSize(settingsFrameWidth, settingsFrameHeight);
				}
					
			}
		});
		
		resetFields.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDefaults();
				
			}
		});
	}

	public static void init() {
		// initialize frame
		frame = new JFrame();

		// initialize layout for panels
		sp = new SpringLayout();

		// initialize holding panels
		tabbedPane = new JTabbedPane();
		settingsPanel = new JPanel();
		cytoscapePanel = new JFXPanel();

		// set settingsPanel layout to sp (defined earlier)
		settingsPanel.setLayout(sp);

		// formatter to restrict TextBoxes to numbers only
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);

		// initialize settings components
		inputDataFileSelector = new JButton("Select Input File");
		geneSetFileSelector = new JButton("Select Gene Set File");
		classInfoFileSelector = new JButton("Select Class File");
		edf = new JCheckBox("Edge direction fixed?");
		minGeneSetSize = new JFormattedTextField(formatter);
		maxGeneSetSize = new JFormattedTextField(formatter);
		numThreads = new JFormattedTextField(formatter);
		pVal = new JFormattedTextField(formatter);
		quickPermEnabled = new JRadioButton("Quick permutations enabled?");
		numNetStruc = new JFormattedTextField(formatter);
		numPerms = new JFormattedTextField(formatter);
		runEDDY = new JButton("Run EDDY");
		output = new JTextArea("CONSOLE OUTPUT:\n\nWelcome to EDDY \nPlease input arguments and click \"run EDDY\"\n");
		showAdvancedOptions = new JCheckBox("Show advanced options", false);
		resamplingRate = new JFormattedTextField(formatter);
		priorDirectionality = new JCheckBox("<html>Use directionality of <br>prior edges?</html>");
		includeNeighbor = new JCheckBox("<html>Include neighbor-of <br>interactions from prior?</html>");
		approximatePermuations = new JRadioButton("Approximate Permuations?");
		priorWeight = new JFormattedTextField(formatter);
		resetFields = new JButton("Reset");
		
		//cuatomize console window
		output.setBackground(Color.BLACK);
		output.setForeground(Color.WHITE);
		output.setFont(new Font("Serif", Font.TYPE1_FONT, 14));
		output.setEditable(false);
		output.setPreferredSize(new Dimension(500,350));
		output.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),BorderFactory.createEmptyBorder(5,5,5,5)));
		
		//set preferred dimension size
		minGeneSetSize.setPreferredSize(new Dimension(100,25));
		maxGeneSetSize.setPreferredSize(new Dimension(100,25));
		numThreads.setPreferredSize(new Dimension(100,25));
		pVal.setPreferredSize(new Dimension(100,25));
		numNetStruc.setPreferredSize(new Dimension(100,25));
		numPerms.setPreferredSize(new Dimension(100,25));
		resamplingRate.setPreferredSize(new Dimension(100, 25));
		priorWeight.setPreferredSize(new Dimension(100, 25));
		
		//initialize labels
		lbl0 = new JLabel("Show advanced options?");
		lbl1 = new JLabel("Select Input File");
		lbl2 = new JLabel("Select Gene Set file");
		lbl3 = new JLabel("Select Class File");
		lbl4 = new JLabel("<html>Edge-Direction Fixed<br>Boolean Parameter</html>");
		lbl5 = new JLabel("Minumum Gene Set size");
	    lbl6 = new JLabel("Maximum Gene Set size");
		lbl7 = new JLabel("Number of threads");
		lbl8 = new JLabel("p-value");
		lbl9 = new JLabel("Quick permutations enabled");
		lbl10 = new JLabel("Number of network structures");
		lbl11 = new JLabel("Number of permutations");
		lbl12 = new JLabel("Resampling Rate");
		lbl13 = new JLabel("Use prior directionality");
		lbl14 = new JLabel("Include Neighbor");
		lbl15 = new JLabel("Approximate Permutations");
		lbl16 = new JLabel("Prior weight");
		
		//QP and AP are in a single button group
		ButtonGroup bg = new ButtonGroup();
		bg.add(quickPermEnabled);
		bg.add(approximatePermuations);
		
		//Java FX, create the cytoscape panel with the given html
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	
            	initFX(cytoscapePanel);
                
            }
       });
		
		
	}

	public static void add() {
		
		// add to settings panel
		settingsPanel.add(lbl0);
		settingsPanel.add(showAdvancedOptions);
		settingsPanel.add(lbl1);
		settingsPanel.add(inputDataFileSelector);
		settingsPanel.add(lbl2);
		settingsPanel.add(geneSetFileSelector);
		settingsPanel.add(lbl3);
		settingsPanel.add(classInfoFileSelector);
		settingsPanel.add(lbl4);
		settingsPanel.add(edf);
		settingsPanel.add(lbl5);
		settingsPanel.add(minGeneSetSize);
		settingsPanel.add(lbl6);
		settingsPanel.add(maxGeneSetSize);
		settingsPanel.add(lbl7);
		settingsPanel.add(numThreads);
		settingsPanel.add(lbl8);
		settingsPanel.add(pVal);
		settingsPanel.add(lbl9);
		settingsPanel.add(quickPermEnabled);
		settingsPanel.add(lbl10);
		settingsPanel.add(numNetStruc);
		settingsPanel.add(lbl11);
		settingsPanel.add(numPerms);
		settingsPanel.add(lbl12);
		settingsPanel.add(resamplingRate);
		settingsPanel.add(lbl13);
		settingsPanel.add(priorDirectionality);
		settingsPanel.add(lbl14);
		settingsPanel.add(includeNeighbor);
		settingsPanel.add(lbl15);
		settingsPanel.add(approximatePermuations);
		settingsPanel.add(lbl16);
		settingsPanel.add(priorWeight);
		settingsPanel.add(runEDDY);
		settingsPanel.add(output);
		settingsPanel.add(resetFields);
		
		// constraints to lay out everything

		// Column 1
		
			//lbl1
				sp.putConstraint(SpringLayout.WEST, lbl1, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl1, 30+5, SpringLayout.NORTH, settingsPanel);
	
			//lbl2
				sp.putConstraint(SpringLayout.WEST, lbl2, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl2, VERT_SPACING, SpringLayout.SOUTH, lbl1);
		
			//lbl3
				sp.putConstraint(SpringLayout.WEST, lbl3, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl3, VERT_SPACING, SpringLayout.SOUTH, lbl2);
				
			//lbl0
				sp.putConstraint(SpringLayout.WEST, lbl0, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl0, VERT_SPACING+10, SpringLayout.SOUTH, lbl3);
			
			//run EDDY
				sp.putConstraint(SpringLayout.WEST, runEDDY, HORIZ_SPACINGC1 -7, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, runEDDY, VERT_SPACING, SpringLayout.SOUTH, lbl0);
		
		// Column2

			sp.putConstraint(SpringLayout.WEST, showAdvancedOptions, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
			sp.putConstraint(SpringLayout.NORTH, showAdvancedOptions, 20, SpringLayout.SOUTH, classInfoFileSelector);
			
			sp.putConstraint(SpringLayout.WEST, inputDataFileSelector, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
			sp.putConstraint(SpringLayout.NORTH, inputDataFileSelector, 30, SpringLayout.NORTH, settingsPanel);
		
			sp.putConstraint(SpringLayout.WEST, geneSetFileSelector, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
			sp.putConstraint(SpringLayout.NORTH, geneSetFileSelector, 10, SpringLayout.SOUTH, inputDataFileSelector);
			
			sp.putConstraint(SpringLayout.WEST, classInfoFileSelector, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
			sp.putConstraint(SpringLayout.NORTH, classInfoFileSelector, 15, SpringLayout.SOUTH, geneSetFileSelector);
			
			
			
		//Column 3
			
			//lbl4
				sp.putConstraint(SpringLayout.WEST, lbl4, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl4, VERT_SPACING, SpringLayout.NORTH, settingsPanel);
		
			//lbl5
				sp.putConstraint(SpringLayout.WEST, lbl5, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl5, VERT_SPACING, SpringLayout.SOUTH, lbl4);
			
			//lbl6
				sp.putConstraint(SpringLayout.WEST, lbl6, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl6, VERT_SPACING, SpringLayout.SOUTH, lbl5);
			
			//lbl7
				sp.putConstraint(SpringLayout.WEST, lbl7, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl7, VERT_SPACING, SpringLayout.SOUTH, lbl6);
				
			//lbl8
				sp.putConstraint(SpringLayout.WEST, lbl8, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl8, VERT_SPACING, SpringLayout.SOUTH, lbl7);
		
			//lbl9
				sp.putConstraint(SpringLayout.WEST, lbl9, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl9, VERT_SPACING, SpringLayout.SOUTH, lbl8);
				
			//lbl10
				sp.putConstraint(SpringLayout.WEST, lbl10, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl10, VERT_SPACING, SpringLayout.SOUTH, lbl9);
				
			//lbl11
				sp.putConstraint(SpringLayout.WEST, lbl11, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl11, VERT_SPACING, SpringLayout.SOUTH, lbl10);
			
			//lbl12
				sp.putConstraint(SpringLayout.WEST, lbl12, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl12, VERT_SPACING, SpringLayout.SOUTH, lbl11);
				
			//lbl13
				sp.putConstraint(SpringLayout.WEST, lbl13, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl13, VERT_SPACING, SpringLayout.SOUTH, lbl12);
				
			//lbl14
				sp.putConstraint(SpringLayout.WEST, lbl14, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl14, VERT_SPACING+10, SpringLayout.SOUTH, lbl13);
			
			//lbl15
				sp.putConstraint(SpringLayout.WEST, lbl15, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl15, VERT_SPACING+10, SpringLayout.SOUTH, lbl14);
			
			//lbl16
				sp.putConstraint(SpringLayout.WEST, lbl16, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, lbl16, VERT_SPACING+10, SpringLayout.SOUTH, lbl15);
				
			//reset button
				sp.putConstraint(SpringLayout.WEST, resetFields, HORIZ_SPACINGC3-10, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, resetFields, VERT_SPACING+10, SpringLayout.SOUTH, lbl16);
				
		//Column 4
				sp.putConstraint(SpringLayout.WEST, edf, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, edf, 30, SpringLayout.NORTH, settingsPanel);
				
				sp.putConstraint(SpringLayout.WEST, minGeneSetSize, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, minGeneSetSize, 25, SpringLayout.SOUTH, edf);
				
				sp.putConstraint(SpringLayout.WEST, maxGeneSetSize, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, maxGeneSetSize, 15, SpringLayout.SOUTH, minGeneSetSize);
				
				sp.putConstraint(SpringLayout.WEST, numThreads, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, numThreads, 15, SpringLayout.SOUTH, maxGeneSetSize);
				
				sp.putConstraint(SpringLayout.WEST, pVal, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, pVal, 15, SpringLayout.SOUTH, numThreads);
				
				sp.putConstraint(SpringLayout.WEST, quickPermEnabled, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, quickPermEnabled, 20, SpringLayout.SOUTH, pVal);
				
				sp.putConstraint(SpringLayout.WEST, numNetStruc, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, numNetStruc, 15, SpringLayout.SOUTH, quickPermEnabled);
				
				sp.putConstraint(SpringLayout.WEST, numPerms, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, numPerms, 15, SpringLayout.SOUTH, numNetStruc);
				

				sp.putConstraint(SpringLayout.WEST, resamplingRate, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, resamplingRate, 15, SpringLayout.SOUTH, numPerms);
			
				sp.putConstraint(SpringLayout.WEST, priorDirectionality, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, priorDirectionality, 15, SpringLayout.SOUTH, resamplingRate);
		
				sp.putConstraint(SpringLayout.WEST, includeNeighbor, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, includeNeighbor, 10, SpringLayout.SOUTH, priorDirectionality);
				
				sp.putConstraint(SpringLayout.WEST, approximatePermuations, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, approximatePermuations, 20, SpringLayout.SOUTH, includeNeighbor);
				
				sp.putConstraint(SpringLayout.WEST, priorWeight, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, priorWeight, 30, SpringLayout.SOUTH, approximatePermuations);
				
			//output box
				sp.putConstraint(SpringLayout.WEST, output, HORIZ_SPACINGC1 -5 , SpringLayout.WEST, settingsPanel);
				sp.putConstraint(SpringLayout.NORTH, output, 375, SpringLayout.NORTH, settingsPanel);
			
			// add to tabbed pane
		tabbedPane.add("Configure", settingsPanel);
		tabbedPane.add("Result", cytoscapePanel);

		// add to main frame
		frame.add(tabbedPane);
	}

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread, create the scene to hold all of the elements
    	Scene scene = createScene();
        fxPanel.setScene(scene);
       
    }

    private static Scene createScene() {
        Group  root  =  new  Group();
        Scene  scene  =  new  Scene(root);
        
        //Webview used to display JS
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        URL myURL = GUI.class.getResource("index.html"); //the source file is set from within the GUI class
        webEngine.load(myURL.toExternalForm());
        root.getChildren().add(browser);
        
        return (scene);
    }
    
	public static void show() {
		frame.setSize(settingsFrameWidth, settingsFrameHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//hide advanced parameters by default
		edf.setVisible(false);
		minGeneSetSize.setVisible(false);
		maxGeneSetSize.setVisible(false);
		numThreads.setVisible(false);
		pVal.setVisible(false);
		quickPermEnabled.setVisible(false);
		numNetStruc.setVisible(false);
		numPerms.setVisible(false);
		
		lbl4.setVisible(false);
		lbl5.setVisible(false);
		lbl6.setVisible(false);
		lbl7.setVisible(false);
		lbl8.setVisible(false);
		lbl9.setVisible(false);
		lbl10.setVisible(false);
		lbl11.setVisible(false);
		
		frame.setVisible(true);

	}
}