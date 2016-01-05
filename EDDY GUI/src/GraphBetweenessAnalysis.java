
import java.util.Vector;

import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class GraphBetweenessAnalysis {
	
	Graph<String, String> gClass1;
	Graph<String, String> gClass2;
	Graph<String, String> gBothClasses;
	Vector<EDDYNode> genes;
	BetweennessCentrality<String, String> btwClass1;
	BetweennessCentrality<String, String> btwClass2;
	BetweennessCentrality<String, String> btwBothClasses;
	
	public GraphBetweenessAnalysis(Vector<EDDYNode> genes) {
		this.genes = genes;
		
		 gClass1 = new UndirectedSparseGraph<>();
		 gClass2 = new UndirectedSparseGraph<>();
		 gBothClasses = new UndirectedSparseGraph<>();

		for (int i = 0; i < genes.size(); i++) {
			gClass1.addVertex(genes.get(i).name);
			gClass2.addVertex(genes.get(i).name);
			gBothClasses.addVertex(genes.get(i).name);
		}
	}
	
	public void addEdge(String source, String target, int graphClass){
		switch(graphClass){
		case 1:
			gClass1.addEdge(source+target, source, target);
			gBothClasses.addEdge(source+target, source, target);
			break;
		case 2:
			gClass2.addEdge(source+target, source, target);
			gBothClasses.addEdge(source+target, source, target);
			break;
		case 3:
			gClass1.addEdge(source+target, source, target);
			gClass2.addEdge(source+target, source, target);
			gBothClasses.addEdge(source+target, source, target);
			break;
		
		}
	}
	public void betweenessCentrality(){
		btwClass1 = new BetweennessCentrality<>(gClass1, true);
		btwClass2 = new BetweennessCentrality<>(gClass2, true);
		btwBothClasses = new BetweennessCentrality<>(gBothClasses, true);
		
		btwClass1.setRemoveRankScoresOnFinalize(false);
		btwClass2.setRemoveRankScoresOnFinalize(false);
		btwBothClasses.setRemoveRankScoresOnFinalize(false);
		btwClass1.evaluate();
		btwClass2.evaluate();
		btwBothClasses.evaluate();			
	}
	
	public double getNodeBtwClass1(String vertexName){
		return btwClass1.getVertexRankScore(vertexName);
	}
	
	public double getNodeBtwClass2(String vertexName){
		return btwClass2.getVertexRankScore(vertexName);
	}
	
	public double getNodeBtwBothClasses(String vertexName){
		return btwBothClasses.getVertexRankScore(vertexName);
	}
}
