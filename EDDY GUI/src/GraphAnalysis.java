
import java.util.Map;
import java.util.Vector;

import com.sun.javafx.font.Metrics;

import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class GraphAnalysis {
	
	Graph<String, String> gClass1;
	Graph<String, String> gClass2;
	Graph<String, String> gBothClasses;
	Vector<EDDYNode> genes;
	BetweennessCentrality<String, String> btwClass1;
	BetweennessCentrality<String, String> btwClass2;
	BetweennessCentrality<String, String> btwBothClasses;
	
	ClosenessCentrality<String, String> clClass1;
	ClosenessCentrality<String, String> clClass2;
	ClosenessCentrality<String, String> clBothClasses;
	
	public GraphAnalysis(Vector<EDDYNode> genes) {
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
	
	public double clusteringCoefficients1(String vertexName){
		edu.uci.ics.jung.algorithms.metrics.Metrics metric = new edu.uci.ics.jung.algorithms.metrics.Metrics();
		Map<String, Double> map1 = metric.clusteringCoefficients(gClass1);
		return map1.get(vertexName);
	}
	
	public double clusteringCoefficients2(String vertexName){
		edu.uci.ics.jung.algorithms.metrics.Metrics metric = new edu.uci.ics.jung.algorithms.metrics.Metrics();
		Map<String, Double> map2 = metric.clusteringCoefficients(gClass2);
		return map2.get(vertexName);
	}
	
	public void closnessCentrality(){
		clClass1 = new ClosenessCentrality<>(gClass1);
		clClass2 = new ClosenessCentrality<>(gClass2);
		clBothClasses = new ClosenessCentrality<>(gBothClasses);
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
