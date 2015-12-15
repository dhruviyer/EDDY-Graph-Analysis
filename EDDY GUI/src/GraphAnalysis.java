import java.util.Arrays;
import java.util.Vector;

import eddy.BetweenessNode;
import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class GraphAnalysis {
	
	Graph<String, String> gClass1;
	Graph<String, String> gClass2;
	Graph<String, String> gBothClasses;
	Vector<EDDYNode> genes;
	
	public GraphAnalysis(Vector<EDDYNode> genes) {
		this.genes = genes;
		
		Graph<String, String> gClass1 = new UndirectedSparseGraph<>();
		Graph<String, String> gClass2 = new UndirectedSparseGraph<>();
		Graph<String, String> gBothClasses = new UndirectedSparseGraph<>();

		for (int i = 0; i < genes.size(); i++) {
			gClass1.addVertex(genes.get(i).name);
			gClass2.addVertex(genes.get(i).name);
			gBothClasses.addVertex(genes.get(i).name);
		}
	}
	public MyBetweenessNode[] betweenessCentrality(){
		BetweennessCentrality<String, String> btwClass1 = new BetweennessCentrality<>(gClass1, true);
		BetweennessCentrality<String, String> btwClass2 = new BetweennessCentrality<>(gClass2, true);
		BetweennessCentrality<String, String> btwBothClasses = new BetweennessCentrality<>(gBothClasses, true);
		
		btwClass1.setRemoveRankScoresOnFinalize(false);
		btwClass2.setRemoveRankScoresOnFinalize(false);
		btwBothClasses.setRemoveRankScoresOnFinalize(false);
		btwClass1.evaluate();
		btwClass2.evaluate();
		btwBothClasses.evaluate();
		
		MyBetweenessNode[] nodes = new MyBetweenessNode[genes.size()];
		
		for(int i = 0; i<genes.size(); i++){
			nodes[i] = new MyBetweenessNode(genes.get(i).name, btwBothClasses.getVertexRankScore(genes.get(i).name));
		}			
		Arrays.sort(nodes);
		return nodes;
	}
}
