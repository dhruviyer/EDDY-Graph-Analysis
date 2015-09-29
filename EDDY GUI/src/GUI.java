import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import org.omg.CORBA.portable.InputStream;

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
	static JCheckBox quickPermEnabled;
	static JFormattedTextField numNetStruc;
	static JFormattedTextField numPerms;
	static JButton runEDDY;
	static JTextArea output;
	static JCheckBox showAdvancedOptions;
	static JFormattedTextField resamplingRate;
	static JCheckBox priorDirectionality;
	static JCheckBox includeNeighbor;
	static JCheckBox approximatePermuations;
	static JFormattedTextField priorWeight;
	static JButton resetFields;
	static JFormattedTextField maxNumParents;
	static JScrollPane outputscroll;
	
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
	static JLabel lbl17;
	static JLabel lbl18;

	// various panes and containers
	static JTabbedPane tabbedPane;
	static JPanel settingsPanel;
	static JFXPanel cytoscapePanel;
	static JScrollPane scrollPane;
	static JFrame frame;

	static SpringLayout sp;

	// layout constants
	static final int VERT_SPACING = 15; // vertical spacing for layout
	static final int HORIZ_SPACINGC1 = 15; // horizontal spacing for column 1 of
											// layout
	static final int HORIZ_SPACINGC2 = 200; // horizontal spacing for column 2
											// in layout
	static final int HORIZ_SPACINGC3 = 530; // horizontal spacing for column 3
											// of layout
	static final int HORIZ_SPACINGC4 = 765; // horizontal spacing for column 3
											// of layout

	static int settingsFrameHeight = 550;
	static int settingsFrameWidth = 570;

	// files
	static String inputFile;
	static String geneSetFile;
	static String classInfoFile;

	// file selector
	static JFileChooser fc;

	public static void main(String[] args) {
		init(); // initialize elements
		add(); // add elements to panels and frames
		setListeners(); // add listeners
		setDefaults(); // set any and all default values
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
				if (!showAdvancedOptions.isSelected()) {
					settingsFrameHeight = 550;
					settingsFrameWidth = 570;
					frame.setSize(settingsFrameWidth, settingsFrameHeight);

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
					maxNumParents.setVisible(false);
					resetFields.setVisible(false);

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
					lbl17.setVisible(false);

				} else {
					settingsFrameHeight = 550;
					settingsFrameWidth = 1025;
					frame.setSize(settingsFrameWidth, settingsFrameHeight);

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
					maxNumParents.setVisible(true);
					resetFields.setVisible(true);

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
					lbl16.setVisible(true);
					lbl17.setVisible(true);
				}

			}
		});

		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 1) {
					frame.setSize(850, 675);
				} else {
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

		inputDataFileSelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(settingsPanel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					inputFile = fc.getSelectedFile().getPath();
					inputDataFileSelector.setText(fc.getSelectedFile().getName());
				}

			}
		});

		geneSetFileSelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(settingsPanel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					geneSetFile = fc.getSelectedFile().getPath();
					geneSetFileSelector.setText(fc.getSelectedFile().getName());
				}

			}
		});

		classInfoFileSelector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(settingsPanel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					classInfoFile = fc.getSelectedFile().getPath();
					classInfoFileSelector.setText(fc.getSelectedFile().getName());
				}

			}
		});

		approximatePermuations.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!approximatePermuations.isSelected()) {
					quickPermEnabled.setEnabled(true);
					quickPermEnabled.setSelected(true);
				} else {
					quickPermEnabled.setEnabled(false);
					quickPermEnabled.setSelected(false);
				}
			}
		});

		runEDDY.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				output.setText("");
				try {
					ProcessBuilder pb = new ProcessBuilder("java", "-jar", "eddy_mt-v2_5_3.jar");
					//pb.redirectErrorStream(true);
					
					Process p = pb.start();
					java.io.InputStream is = p.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					for (String line = br.readLine(); line != null; line = br.readLine()) {
						output.append(line+"\n"); // Or just ignore it
					}
					p.waitFor();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
		scrollPane = new JScrollPane(settingsPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		settingsPanel.setPreferredSize(new Dimension(1000, 550));
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
		quickPermEnabled = new JCheckBox("Quick permutations enabled?");
		numNetStruc = new JFormattedTextField(formatter);
		numPerms = new JFormattedTextField(formatter);
		runEDDY = new JButton("Run EDDY");
		output = new JTextArea("Welcome to EDDY \nPlease input arguments and click \"run EDDY\"\n");
		showAdvancedOptions = new JCheckBox("Show advanced options", false);
		resamplingRate = new JFormattedTextField(formatter);
		priorDirectionality = new JCheckBox("<html>Use directionality of <br>prior edges?</html>");
		includeNeighbor = new JCheckBox("<html>Include neighbor-of <br>interactions from prior?</html>");
		approximatePermuations = new JCheckBox("Approximate Permuations?");
		priorWeight = new JFormattedTextField(formatter);
		resetFields = new JButton("Reset");
		maxNumParents = new JFormattedTextField(formatter);

		// cuatomize console window
		output.setBackground(Color.BLACK);
		output.setForeground(Color.WHITE);
		output.setFont(new Font("Serif", Font.TYPE1_FONT, 14));
		output.setEditable(false);
		output.setPreferredSize(new Dimension(500, 1500));
		output.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		// qp not enabled originally
		quickPermEnabled.setEnabled(false);

		outputscroll = new JScrollPane(output,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		outputscroll.setPreferredSize(new Dimension(500, 315));
		
		// set preferred dimension size
		minGeneSetSize.setPreferredSize(new Dimension(100, 25));
		maxGeneSetSize.setPreferredSize(new Dimension(100, 25));
		numThreads.setPreferredSize(new Dimension(100, 25));
		pVal.setPreferredSize(new Dimension(100, 25));
		numNetStruc.setPreferredSize(new Dimension(100, 25));
		numPerms.setPreferredSize(new Dimension(100, 25));
		resamplingRate.setPreferredSize(new Dimension(100, 25));
		priorWeight.setPreferredSize(new Dimension(100, 25));
		maxNumParents.setPreferredSize(new Dimension(100, 25));

		// initialize labels
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
		lbl17 = new JLabel("Max parent nodes");
		lbl18 = new JLabel("Console output:");

		// set description tooltips
		inputDataFileSelector.setToolTipText("Input data file");
		geneSetFileSelector.setToolTipText("Gene set list file");
		classInfoFileSelector.setToolTipText("Glass information file");
		edf.setToolTipText("Edge-direction fixed boolean parameter");
		minGeneSetSize.setToolTipText("Minimum gene set size for analysis. Default = -1 for no limit.");
		maxGeneSetSize.setToolTipText("Maximum gene set size for analysis. Default = -1 for no limit.");
		numThreads.setToolTipText("Number of threads to use");
		maxNumParents.setToolTipText("Maximum number of parents for each node");
		pVal.setToolTipText("pvalue threshold for independence testing. [default = 0.05]");
		quickPermEnabled.setToolTipText(
				"<html>Quick permutations enabled -- permutation stops <br>when there is no possibility to yield p-value less than given threshold.</html>");
		numNetStruc.setToolTipText("Number network structures to consider [default = LOO (-1)]");
		numPerms.setToolTipText("Number of permutations for statistical significance testing. [default = 1000]");
		resamplingRate
				.setToolTipText("Resampling rate. [default = 0.8 when number of network structures is specified]");
		priorDirectionality.setToolTipText(" Use directionality of prior edges. [default = false]");
		includeNeighbor.setToolTipText("Include neighbor-of interactions from prior.  [default false]");
		approximatePermuations.setToolTipText("Approximate permutations with beta distribution.  [default true]");
		priorWeight.setToolTipText("Weight to give prior. [default = 0.0]");

		// initialize file selector
		fc = new JFileChooser();

		// Java FX, create the cytoscape panel with the given html
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
		settingsPanel.add(outputscroll);
		settingsPanel.add(lbl17);
		settingsPanel.add(maxNumParents);
		settingsPanel.add(resetFields);
		settingsPanel.add(lbl18);

		// constraints to lay out everything

		// Column 1

		// lbl1
		sp.putConstraint(SpringLayout.WEST, lbl1, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl1, VERT_SPACING, SpringLayout.NORTH, settingsPanel);

		// lbl2
		sp.putConstraint(SpringLayout.WEST, lbl2, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl2, VERT_SPACING + 5, SpringLayout.SOUTH, lbl1);

		// lbl3
		sp.putConstraint(SpringLayout.WEST, lbl3, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl3, VERT_SPACING + 5, SpringLayout.SOUTH, lbl2);

		// lbl0
		sp.putConstraint(SpringLayout.WEST, lbl0, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl0, VERT_SPACING + 3, SpringLayout.SOUTH, lbl3);

		// run EDDY
		sp.putConstraint(SpringLayout.WEST, runEDDY, HORIZ_SPACINGC1 - 7, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, runEDDY, VERT_SPACING, SpringLayout.SOUTH, lbl0);

		// lbl18
		sp.putConstraint(SpringLayout.WEST, lbl18, HORIZ_SPACINGC1, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl18, VERT_SPACING, SpringLayout.SOUTH, runEDDY);

		// Column2

		sp.putConstraint(SpringLayout.WEST, showAdvancedOptions, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, showAdvancedOptions, 10, SpringLayout.SOUTH, classInfoFileSelector);

		sp.putConstraint(SpringLayout.WEST, inputDataFileSelector, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, inputDataFileSelector, VERT_SPACING - 5, SpringLayout.NORTH,
				settingsPanel);

		sp.putConstraint(SpringLayout.WEST, geneSetFileSelector, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, geneSetFileSelector, 5, SpringLayout.SOUTH, inputDataFileSelector);

		sp.putConstraint(SpringLayout.WEST, classInfoFileSelector, HORIZ_SPACINGC2, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, classInfoFileSelector, 5, SpringLayout.SOUTH, geneSetFileSelector);

		// Column 3

		// lbl4
		sp.putConstraint(SpringLayout.WEST, lbl4, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl4, VERT_SPACING, SpringLayout.NORTH, settingsPanel);

		// lbl5
		sp.putConstraint(SpringLayout.WEST, lbl5, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl5, VERT_SPACING, SpringLayout.SOUTH, lbl4);

		// lbl6
		sp.putConstraint(SpringLayout.WEST, lbl6, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl6, VERT_SPACING, SpringLayout.SOUTH, lbl5);

		// lbl7
		sp.putConstraint(SpringLayout.WEST, lbl7, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl7, VERT_SPACING, SpringLayout.SOUTH, lbl6);

		// lbl8
		sp.putConstraint(SpringLayout.WEST, lbl8, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl8, VERT_SPACING, SpringLayout.SOUTH, lbl7);

		// lbl9
		sp.putConstraint(SpringLayout.WEST, lbl9, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl9, VERT_SPACING, SpringLayout.SOUTH, lbl8);

		// lbl15
		sp.putConstraint(SpringLayout.WEST, lbl15, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl15, VERT_SPACING + 2, SpringLayout.SOUTH, lbl9);

		// lbl10
		sp.putConstraint(SpringLayout.WEST, lbl10, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl10, VERT_SPACING, SpringLayout.SOUTH, lbl15);

		// lbl11
		sp.putConstraint(SpringLayout.WEST, lbl11, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl11, VERT_SPACING, SpringLayout.SOUTH, lbl10);

		// lbl12
		sp.putConstraint(SpringLayout.WEST, lbl12, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl12, VERT_SPACING, SpringLayout.SOUTH, lbl11);

		// lbl13
		sp.putConstraint(SpringLayout.WEST, lbl13, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl13, VERT_SPACING + 5, SpringLayout.SOUTH, lbl12);

		// lbl14
		sp.putConstraint(SpringLayout.WEST, lbl14, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl14, VERT_SPACING + 10, SpringLayout.SOUTH, lbl13);

		// lbl16
		sp.putConstraint(SpringLayout.WEST, lbl16, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl16, VERT_SPACING + 15, SpringLayout.SOUTH, lbl14);

		// lbl17
		sp.putConstraint(SpringLayout.WEST, lbl17, HORIZ_SPACINGC3, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, lbl17, VERT_SPACING + 10, SpringLayout.SOUTH, lbl16);

		// reset button
		sp.putConstraint(SpringLayout.WEST, resetFields, HORIZ_SPACINGC3 - 10, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, resetFields, VERT_SPACING, SpringLayout.SOUTH, lbl17);

		// Column 4
		sp.putConstraint(SpringLayout.WEST, edf, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, edf, 20, SpringLayout.NORTH, settingsPanel);

		sp.putConstraint(SpringLayout.WEST, minGeneSetSize, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, minGeneSetSize, 15, SpringLayout.SOUTH, edf);

		sp.putConstraint(SpringLayout.WEST, maxGeneSetSize, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, maxGeneSetSize, 5, SpringLayout.SOUTH, minGeneSetSize);

		sp.putConstraint(SpringLayout.WEST, numThreads, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, numThreads, 5, SpringLayout.SOUTH, maxGeneSetSize);

		sp.putConstraint(SpringLayout.WEST, pVal, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, pVal, 5, SpringLayout.SOUTH, numThreads);

		sp.putConstraint(SpringLayout.WEST, quickPermEnabled, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, quickPermEnabled, 10, SpringLayout.SOUTH, pVal);

		sp.putConstraint(SpringLayout.WEST, approximatePermuations, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, approximatePermuations, 10, SpringLayout.SOUTH, quickPermEnabled);

		sp.putConstraint(SpringLayout.WEST, numNetStruc, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, numNetStruc, 5, SpringLayout.SOUTH, approximatePermuations);

		sp.putConstraint(SpringLayout.WEST, numPerms, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, numPerms, 5, SpringLayout.SOUTH, numNetStruc);

		sp.putConstraint(SpringLayout.WEST, resamplingRate, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, resamplingRate, 5, SpringLayout.SOUTH, numPerms);

		sp.putConstraint(SpringLayout.WEST, priorDirectionality, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, priorDirectionality, 5, SpringLayout.SOUTH, resamplingRate);

		sp.putConstraint(SpringLayout.WEST, includeNeighbor, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, includeNeighbor, 10, SpringLayout.SOUTH, priorDirectionality);

		sp.putConstraint(SpringLayout.WEST, priorWeight, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, priorWeight, 15, SpringLayout.SOUTH, includeNeighbor);

		sp.putConstraint(SpringLayout.WEST, maxNumParents, HORIZ_SPACINGC4, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, maxNumParents, 15, SpringLayout.SOUTH, priorWeight);

		// output box
		sp.putConstraint(SpringLayout.WEST, outputscroll, HORIZ_SPACINGC1 - 5, SpringLayout.WEST, settingsPanel);
		sp.putConstraint(SpringLayout.NORTH, outputscroll, 217, SpringLayout.NORTH, settingsPanel);

		// add to tabbed pane
		// tabbedPane.add("Configure", settingsPanel);
		tabbedPane.add("Configure", scrollPane);
		tabbedPane.add("Result", cytoscapePanel);

		// add to main frame
		frame.add(tabbedPane);
	}

	private static void initFX(JFXPanel fxPanel) {
		// This method is invoked on the JavaFX thread, create the scene to hold
		// all of the elements
		Scene scene = createScene();
		fxPanel.setScene(scene);

	}

	private static Scene createScene() {
		Group root = new Group();
		Scene scene = new Scene(root);

		// Webview used to display JS
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		URL myURL = GUI.class.getResource("index.html"); // the source file is
															// set from within
															// the GUI class
		webEngine.load(myURL.toExternalForm());
		root.getChildren().add(browser);

		return (scene);
	}

	public static void show() {
		frame.setSize(settingsFrameWidth, settingsFrameHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("EDDY: Evaluation of Differential DependencY");

		// hide advanced parameters by default
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
		maxNumParents.setVisible(false);
		resetFields.setVisible(false);

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
		lbl17.setVisible(false);
		resetFields.setVisible(false);

		frame.setVisible(true);

	}
}