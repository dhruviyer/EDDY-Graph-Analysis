

public class MyBetweenessNode implements Comparable<MyBetweenessNode>{
	private double rankScore;
	private String name;
	
	public MyBetweenessNode(String name, double rankScore){
		this.name = name;
		this.rankScore = rankScore;
	}
	
	public double getRankScore() {
		return rankScore;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(MyBetweenessNode o) {
		// TODO Auto-generated method stub
		return Double.compare(o.rankScore, this.rankScore);
	}
	
}
