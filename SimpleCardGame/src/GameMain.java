
/*
 * Et enkelt kortspill: 
 * 
 * Du trekker et kort og gjetter om neste kort har
 * høyere eller lavere verdi. 
 * Du får 1 poeng hvis du gjetter riktig eller får lik verdi.
 * Hvis du gjetter feil avsluttes spillet.
 * Programmet lagrer resultatene på fil.
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GameMain {
	
	private static Player p;
	private static int score = 0;
	private static int card1 = 0;
	private static int card2 = 0;
	
	
	public static void main(String[] args) {	
		Scanner scan = new Scanner(System.in);
		int key;
		System.out.println("\nVelkommen til et enkelt kortspill!"
				+ "\n\nDu trekker et kort og gjetter om neste kort har "
				+ "\nhøyere eller lavere verdi."
				+ "\nDu får 1 poeng hvis du gjetter riktig eller får lik verdi.");
		
		do {	
			System.out.println("\n\n*** Kortspill meny ***");
			System.out.println("\n0 - Avslutt og lagre resultat");
			System.out.println("1 - Nytt spill");
			System.out.println("2 - Se resultatliste");
			System.out.println("Valg: "); 
			key = scan.nextInt();			
			
			switch (key) {		
				case 0: try {
						if(p.getScore() > 0) {
							System.out.println("\nRegistrert:");
							System.out.println(p.getName() + " -> " + p.getScore() + " poeng");
							saveScore(p.name, p.totalScore);
						}																				
					} catch (NullPointerException e) {
					
					} finally {
					System.out.println("\nAvslutter..");
					}
					break;
				case 1: startGame();
					break;
				case 2: readFile();
					break;
				default: System.out.println("\nUgyldig kommando.\n"); 
			} 			
		} while(key != 0);  
		scan.close();		
			
	}
			
	
	public static void startGame() {
		Scanner scan = new Scanner(System.in);
		String name;	
		System.out.println("Tast inn ditt fornavn: ");
		name = scan.nextLine();
		p = new Player(name, 0);
		char input;	
		boolean end = false;
		
		drawCard();
						
		do {
			card2 = card1;
			System.out.println("\nGår du opp eller ned? \nTrykk o eller n (eller a for å avslutte spillet): ");
			input = (char) scan.next().charAt(0);
			
			if(input == ('o')) {			
				drawCard();	
			
				if(goUp(card2, card1) == true) {					
					System.out.println("\nGratulerer!");
					score++;
				} else if(goUp(card2, card1) == false) {
					System.out.println("\nDet var dessverre feil.");
					System.out.println("\nDU FIKK " + score + " POENG!" + "\n\nSpillet avsluttes.");
					setScore();
					end = true;
					return;
				}
			} else if(input == ('n')) {
				drawCard();
				if(goDown(card2, card1) == true) {					
					System.out.println("\nGratulerer!");
					score++;
				} else if(goDown(card2, card1) == false) {
					System.out.println("\nDet var dessverre feil.");
					System.out.println("\nDU FIKK " + score + " POENG!" + "\n\nSpillet avsluttes.");
					setScore();
					end = true;
					return;
				}
			} else if(input == ('a')) {
				setScore();
				end = true;
				return;
			} else {
				System.out.print("Ugyldig svar. \nVennligst tast på nytt: ");
				input = (char) scan.next().charAt(0);
			}	
			
		} while((input != 'a') || (end == false)); 	
		scan.close();
	}
		
	public static void drawCard() {
		System.out.println("\nTrekker nytt kort: ");
		Card c = new Card();		
		
		int cardNumb = c.getNumber();
		String cardNumber = "" + cardNumb;
		card1 = cardNumb;
		
		if(cardNumb == 1) {
			cardNumber = "1 Ess";
		} else if(cardNumb == 11) {
			cardNumber = "11 Knekt";
		} else if(cardNumb == 12) {
			cardNumber = "12 Dronning";
		} else if(cardNumb == 13) {
			cardNumber = "13 Konge";
		}
		
		int colorNumb = c.getColor();
		String cardColor = null;
		if(colorNumb == 1) {
			//cardColor = "Hjerter";
			cardColor = "\u2665";
		} else if(colorNumb == 2) {
			//cardColor = "Ruter";
			cardColor = "\u2666";
		} else if(colorNumb == 3) {
			//cardColor = "Kløver";
			cardColor = "\u2663";
		} else if(colorNumb == 4) {
			cardColor = "\u2660";
		}	
		System.out.println();
		System.out.println("----------------");
		System.out.println("   " + cardNumber.toUpperCase() + " " + cardColor);
		System.out.println("----------------");
	}
	
	public static boolean goUp(int x, int y) {
		if(Integer.compare(x, y) <= 0) {
			card2 = y;
			return true;
		} else {		
			return false;			
		}
	}
	
	public static boolean goDown(int x, int y) {
		if(Integer.compare(x, y) >= 0) {
			card2 = y;
			return true;
		} else {		
			return false;			
		}
	}
		
	public static void setScore() {
		p.setScore(score);
	}
	

	public static void saveScore(String name, int score) {		
		String filename = "/Users/Linda/Desktop/Java/GithubProjects/SimpleCardGame/scores.txt";
		File file = new File(filename);
		
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write("\n" + name + " " + score);
            fw.close();
        }
        catch(IOException e) {
        	System.out.println("ERROR: could not read file: " + filename);
			e.printStackTrace();
        }
	}
	
	public static void readFile() {
		String filename = "/Users/Linda/Desktop/Java/GithubProjects/SimpleCardGame/scores.txt";
		String text;		
		
		File file = new File(filename);	
		System.out.println("\n-------------");
		System.out.println("Resultater:\n");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((text = br.readLine()) != null) {					
				System.out.println(text);	
			} 				
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: file not found: " + filename);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: could not read the data: " + filename);
			e.printStackTrace();
		} 
		System.out.println("-------------");	
	}
}