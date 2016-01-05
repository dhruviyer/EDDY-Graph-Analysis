import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FilesFromOutputGUI {

	int numInputs;
	String eList;
	String nFile;
	String iData;
	String cFile;
	OutputWriter opw;
	
	public FilesFromOutputGUI() {

		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Evaluation of Dependency Differentiality");

		JButton edgelist = new JButton("Select EdgeList.txt");
		JButton nodeinfo = new JButton("Select NODEINFO.txt");
		JButton input = new JButton("Select input dataset");
		JButton classData = new JButton("Select class info file");
		JButton advance = new JButton("Advance");
		JLabel one = new JLabel("");
		JLabel two = new JLabel("");
		JLabel three = new JLabel("");
		JLabel four = new JLabel("");

		edgelist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					numInputs++;
					eList = fc.getSelectedFile().getPath();
					one.setText("\t√");
				}

			}
		});

		nodeinfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					numInputs++;
					nFile = fc.getSelectedFile().getPath();
					two.setText("\t√");
				}

			}
		});

		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					numInputs++;
					iData = fc.getSelectedFile().getPath();
					three.setText("\t√");
				}

			}
		});
		
		classData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					numInputs++;
				cFile = fc.getSelectedFile().getPath();
					four.setText("\t√");
				}

			}
		});
		
		advance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(numInputs >= 4){
					new SummaryTableGenerator().makeTable(eList, nFile, iData, cFile);
					frame.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "ERROR: YOU ARE MISSING ONE OR MORE INPUTS");
				}
				
			}
		});
		
		panel.setLayout(new GridLayout(5, 2));
		panel.add(edgelist);
		panel.add(one);
		panel.add(nodeinfo);
		panel.add(two);
		panel.add(input);
		panel.add(three);
		panel.add(classData);
		panel.add(four);
		panel.add(advance);

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		JOptionPane.showMessageDialog(null, "The following wizard will \nguide you to enter your output \nfiles for post-processing.");
		frame.setVisible(true);

	}
}
