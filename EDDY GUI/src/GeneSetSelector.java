import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GeneSetSelector {
	JFrame frame;
	JPanel panel;
	
	SpringLayout sp;
	JList<String> geneHigherList;
	JScrollPane geneHigherListScrollPane;
	Vector<String> higherGeneSet;

	JList<String> geneSubList;
	JScrollPane geneSubListScrollPane;
	Vector<String> lowerGeneSet;
	
	JButton customGS;
	JButton selectAll;
	JButton go;
	
	public GeneSetSelector(){
		init();
		populate();
		add();
		show();
		
	}
	
	private void init(){
		go = new JButton("Apply");
		customGS = new JButton("Use custom Gene Set");
		selectAll = new JButton("Select All");
		
		sp = new SpringLayout();
		frame = new JFrame("Gene Set Selector");
		panel = new JPanel();
		panel.setLayout(sp);
		
		higherGeneSet = new Vector<String>();
		lowerGeneSet = new Vector<String>();
		
		geneHigherList = new JList<String>();
		geneHigherListScrollPane = new JScrollPane(geneHigherList);
		geneHigherList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		geneHigherList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				populateLower();
				
			}
		});
		
		geneSubList = new JList<String>();
		geneSubListScrollPane = new JScrollPane(geneSubList);
		geneSubList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		geneHigherListScrollPane.setPreferredSize(new Dimension(100, 150));
		geneSubListScrollPane.setPreferredSize(new Dimension(300, 350));
	}
	
	private void populateLower(){
		BufferedReader br;
		String line = "";
		int[] selected = geneHigherList.getSelectedIndices();
		try{
			lowerGeneSet.removeAllElements();
			for(int i = 0; i<selected.length; i++){
				switch(selected[i]){
				case 0:
					br = new BufferedReader(new FileReader("BIOCARTA.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				case 1:
					br = new BufferedReader(new FileReader("KEGG.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				case 2:
					br = new BufferedReader(new FileReader("PID.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				case 3:
					br = new BufferedReader(new FileReader("REACTOME.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				case 4:
					br = new BufferedReader(new FileReader("SA.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				case 5:
					br = new BufferedReader(new FileReader("SIG.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				case 6:
					br = new BufferedReader(new FileReader("ST.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				case 7:
					br = new BufferedReader(new FileReader("WNT.txt"));
				    while ((line = br.readLine()) != null) {
				      lowerGeneSet.addElement(line);
				    }
				    break;
				  
				}
			}
		geneSubList.setListData(lowerGeneSet);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 
	private void populate(){
		higherGeneSet.addElement("BIOCARTA");
		higherGeneSet.addElement("KEGG");
		higherGeneSet.addElement("PID");
		higherGeneSet.addElement("REACTOME");
		higherGeneSet.addElement("SA");
		higherGeneSet.addElement("SIG");
		higherGeneSet.addElement("ST");
		higherGeneSet.addElement("WNT");

		geneHigherList.setListData(higherGeneSet);
	}
	
	private void add(){
		JLabel lbla = new JLabel("Gene Set Source");
		JLabel lblb = new JLabel("Gene Set");
		JLabel lblc = new JLabel("==>");
		
		panel.add(lbla);
		panel.add(lblb);
		panel.add(geneHigherListScrollPane);
		panel.add(geneSubListScrollPane);
		panel.add(lblc);
		panel.add(go);
		panel.add(customGS);
		panel.add(selectAll);
		
		sp.putConstraint(SpringLayout.NORTH, lbla, 1, SpringLayout.NORTH, panel);
		sp.putConstraint(SpringLayout.WEST, lbla, 5, SpringLayout.WEST, panel);
		
		sp.putConstraint(SpringLayout.NORTH, geneHigherListScrollPane, 10, SpringLayout.SOUTH, lbla);
		sp.putConstraint(SpringLayout.WEST, lbla, 5, SpringLayout.WEST, panel);
		
		sp.putConstraint(SpringLayout.NORTH, lblb, 1, SpringLayout.NORTH, panel);
		sp.putConstraint(SpringLayout.WEST, lblb, 200, SpringLayout.WEST, lbla);
		
		sp.putConstraint(SpringLayout.NORTH, geneSubListScrollPane, 10, SpringLayout.SOUTH, lblb);
		sp.putConstraint(SpringLayout.WEST, geneSubListScrollPane, 200, SpringLayout.WEST, geneHigherListScrollPane);
		
		sp.putConstraint(SpringLayout.NORTH, lblc, 100, SpringLayout.SOUTH, lblb);
		sp.putConstraint(SpringLayout.WEST, lblc, 125, SpringLayout.WEST, geneHigherListScrollPane);
		
		sp.putConstraint(SpringLayout.NORTH, selectAll, 100, SpringLayout.SOUTH, geneHigherListScrollPane);
		sp.putConstraint(SpringLayout.WEST, selectAll, 1, SpringLayout.WEST, panel);
		
		sp.putConstraint(SpringLayout.NORTH, customGS, 10, SpringLayout.SOUTH, selectAll);
		sp.putConstraint(SpringLayout.WEST, customGS, 1, SpringLayout.WEST, panel);
		
		sp.putConstraint(SpringLayout.NORTH, go, 10, SpringLayout.SOUTH, customGS);
		sp.putConstraint(SpringLayout.WEST, go, 1, SpringLayout.WEST, panel);
		
		frame.add(panel);
	}
	
	private void show(){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(550, 425);
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
