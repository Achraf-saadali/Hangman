

import java.util.Scanner;

public class HangmanTest {

	public static void main(String[] args) {
		Person p1=new Person("ACHRAF","SAADALI");
		p1.setGame();
		GameSession g1=p1.getGame();
		System.out.println(g1.getWordToGuess());
		
		Scanner scan =new Scanner(System.in);
		while(g1.getTrials_left()!=-1) {
			System.out.println("You are at : "+g1.getTheWord());
			System.out.println("Guess The Word : (A Letter Or Full Word)");
			String PlayerGuess=scan.nextLine();
			g1.changeTheWord(PlayerGuess);
			if(g1.isGame_WIN_Status())
				break;
			
			
			
		}
		scan.close();
		if(g1.getTrials_left()==-1)
			System.out.println("GameOver: "+p1.getFirst_Name()+" "+p1.getLast_Name()+" you have exhausted your trials and lost  ");
		
		

	}

}
