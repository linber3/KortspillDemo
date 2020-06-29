
public class Card {
	
	int number = 0;
	int color = 0;
	final int minNumb = 1;
	final int maxNumb = 13;
	final int minColor = 1;
	final int maxColor = 4;
	
	Card() {	
		number = minNumb + (int) (Math.random() * ((maxNumb - minNumb) + 1));
		color = minColor + (int) (Math.random() * ((maxColor - minColor) + 1));
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getColor() {
		return color;
	}

}
