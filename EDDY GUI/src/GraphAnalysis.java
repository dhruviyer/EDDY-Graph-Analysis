import java.util.Vector;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class GraphAnalysis {
	public GraphAnalysis(Vector<String> genes) {
		Graph<String, String> gClass1 = new UndirectedSparseGraph<>();
		Graph<String, String> gClass2 = new UndirectedSparseGraph<>();
		Graph<String, String> gBothClasses = new UndirectedSparseGraph<>();

		for (int i = 0; i < genes.size(); i++) {
			gClass1.addVertex(genes.get(i));
			gClass2.addVertex(genes.get(i));
			gBothClasses.addVertex(genes.get(i));
		}
	}
}
