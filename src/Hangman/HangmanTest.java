package Hangman;

import java.util.Scanner;

public class HangmanTest {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("The name For the party :");
		String a=sc.nextLine();
		
		String[] k=a.split(" ");
		
		Person p1;
		

		 if(k.length==1) {
			p1=new Person(k[0],"----");
		}
		else if(k.length==2) {
			p1=new Person(k[0],k[1]);
		}
		else  {
			p1=new Person("unknow","player");}
		 

		
	    Loop:
	    	while (true) {
	    	    String m = WannaPlay(sc).toLowerCase();

	    	    if (m.equals("y") || m.equals("yes")) {
	    	        play(p1, sc);
	    	    } else if (m.equals("n") || m.equals("no")) {
	    	    	
	    	    	
	    	    	System.out.println("GoodBye "+p1.getFirst_Name()+" "+p1.getLast_Name()+" .....");
	    	        break; // exit the loop
	    	    } else {
	    	        System.out.println("Query undefined... Try again.");
	    	        continue Loop;
	    	    }
	    	}

		
       
       
       
       
       
       
       
       
       
	    sc.close();
       }  
       
	

	public static void play(Person P,Scanner scan) {
		P.setGame();
		GameSession G = P.getGame();
		
		
		
		if(P.getGames_Count()==1)
			System.out.println("Welcome : Let's start with a new Game 	");
		else {
			System.out.println("Let's Continue.......Your Word is : ");
		}
		

		while (G.getTrials_left() != -1) {
			
			System.out.println("Guess The Word : (A Letter Or Full Word)");
			System.out.println(G.getTheWord());
			String PlayerGuess = scan.nextLine().toLowerCase();

			G.changeTheWord(PlayerGuess);

			if (G.isGame_WIN_Status()) {
				System.out.println("Congrats : " + P.getFirst_Name() + " " + P.getLast_Name() + " have won !!!");
				break;
			}
			
			if(G.getTrials_left()<=2) {
				System.out.println("Do you like a hint say yes if you want it ");
				String HintAnswer=scan.nextLine().toLowerCase();
				if(HintAnswer.equals("yes")) {
					System.out.println(" your hint is : "+G.getQuestionHint());
				}
			}
		}

		if (G.getTrials_left() == -1) {
			System.out.println("Game Over: " + P.getFirst_Name() + " " + P.getLast_Name() + ", you have exhausted your trials and lost. your word was "+G.getWordToGuess());
		}

		
	}
	public static String  WannaPlay(Scanner sc) {
		
		System.out.println("Wanna Play (Y/N): ");
		String m=sc.nextLine();
		
		return m;
		
		
	}
}
