
public class EDDYNode {
	String name;
	double ratio;
	double expr1c1;
	double expr0c1;
	double exprneg1c1;
	double expr1c2;
	double expr0c2;
	double exprneg1c2;
	int edgesc1;
	int edgesc2;
	String nodeEdgeColor;

	public EDDYNode(String name) {
		this.name = name;
	}

	public EDDYNode(String name, double ratio, double expr1c1, double expr0c1, double exprneg1c1, double expr1c2,
			double expr0c2, double exprneg1c2) {
		this.name = name;
		this.expr1c1 = expr1c1;
		this.expr0c1 = expr0c1;
		this.exprneg1c1 = exprneg1c1;
		this.expr1c2 = expr1c2;
		this.expr0c2 = expr0c2;
		this.exprneg1c2 = exprneg1c2;
		this.ratio = ratio;
	}
}
