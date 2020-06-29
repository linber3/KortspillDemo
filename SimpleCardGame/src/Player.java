
public class Player {
	
	String name;
	int totalScore;
	
	Player(String name, int score) {
		this.name = name;
		this.totalScore = score;
	}
	
	public void setScore(int score) {
		totalScore = totalScore + score;
	}
	
	public int getScore() {
		return totalScore;
	}
	
	public String getName() {
		return name;
	}

}
