import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Evaluation of Dependency Differentiality");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		
		JButton runEddy = new JButton("Run EDDY");
		JButton outputOnly = new JButton("Use generated output files");
		
		panel1.add(new JLabel("Welcome to EDDY! Please choose a run configuration."));
		panel2.setLayout(new GridLayout(2,2));
		panel2.add(runEddy);
		panel2.add(outputOnly);
		panel2.add(new JLabel("<html>Click to run full EDDY <br></br>algorthm on input, class, and <br></br>geneset files.<html>"));
		panel2.add(new JLabel("<html>Click to run only output <br></br>post-processing given EdgeList.txt <br></br>and output.txt<html>"));
		
		runEddy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GUI gui = new GUI();
				frame.dispose();
			}
		});
		
		frame.add(panel1);
		frame.add(panel2);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 175);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//new OutputWriter("/Users/diyer/Desktop/BIOCARTA_CLASSIC_PATHWAY_EdgeList.txt", "/Users/diyer/Desktop/BIOCARTA_CLASSIC_PATHWAY_NODEINFO.txt","/Users/diyer/Desktop/TCGA_GBM_mRNA_discrete_HGNC.txt","/Users/diyer/Desktop/Proneural.txt");
	}
}
