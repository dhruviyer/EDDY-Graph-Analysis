import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {

	static String edgelistfile;
	static String nodeinfofile;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Evaluation of Dependency Differentiality");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		JButton runEddy = new JButton("Full EDDY");
		JButton outputOnly = new JButton("<html>Only Post-processing <br></br>with Analysis<html>");

		panel1.add(new JLabel("Welcome to EDDY! Please choose a run configuration."));
		panel2.setLayout(new GridLayout(2, 2));
		panel2.add(runEddy);
		panel2.add(outputOnly);
		panel2.add(new JLabel(
				"<html>Click to run full EDDY <br></br>algorthm on input, class, and <br></br>geneset files.<html>"));
		panel2.add(new JLabel(
				"<html>Click to run only output <br></br>post-processing given output files <br></br>and NODEINFO.txt<html>"));

		runEddy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				GUI gui = new GUI();
				
			}
		});

		outputOnly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new FilesFromOutput();
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

	}
}
