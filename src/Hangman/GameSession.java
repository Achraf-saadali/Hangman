package Hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
public class GameSession {
	private static List<String> All_Words;
	
	 private static int Trials_Max=5;
	 
	 private int Trials_left=Trials_Max;
	
	private String TheWord="";
	
	private static int count;
	
	private int id;
	
	private  String WordToGuess="";
	
	private boolean Game_WIN_Status=false;
	
	private QuestionHint Questionhint;
	
	
	
	static {
		try(BufferedReader FileWord=new BufferedReader(new FileReader("words.txt"))){
			All_Words=FileWord.lines().filter(line->line.length()>=3).collect(Collectors.toList());
		}catch(IOException e) {
			System.err.println("An error has occured when opening this file...");
		}
		
		
		
		
		
	}
	public GameSession() {
		setId(++count);
		setWordToGuess();
		setQuestionhint(new QuestionHint(this.getWordToGuess()));
		
	}
	public  String getWordToGuess() {
		return WordToGuess;
	}
	public  void setWordToGuess() {
        
		Random rand=new Random();
		
		int WordLine=rand.nextInt(0,All_Words.size());
		
		WordToGuess=All_Words.get(WordLine);
		setTheWord(WordToGuess.length());}
		
	
	
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	
	public String getTheWord() {
		return TheWord;
	}
	
	
	public void setTheWord(int Length) {
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<Length;i++) {
			sb.append("-");}
		TheWord=sb.toString();
	}
	
	
	 private boolean checkCompatbility(String PlayerWordGuess) {
		
		 return PlayerWordGuess.equals(WordToGuess); }
	 
	public void changeTheWord(String Tryout) {
		boolean Letter_Guess=false;
		
		if(checkCompatbility(Tryout)) {
			TheWord=Tryout;
			System.out.println("The Game Has finished With success !!!,the word was : "+TheWord);
			
			setGame_WIN_Status(true);
			
			
			return;}
		
		if(Tryout.length()!=1) {
			System.out.println("Incorrect Guess ............ Another Foul ");
			setTrials_left();
			return;}
		
		
		int n=WordToGuess.length();
		char Try=Tryout.charAt(0);
		
		StringBuilder sb= new StringBuilder();
		
		for(int i=0;i<n;i++) {
			char WordToGuess_Letter=WordToGuess.charAt(i);
			char TheWord_Letter=TheWord.charAt(i);
			
			if(WordToGuess_Letter==Try) {
				Letter_Guess=true;
				sb.append(Tryout);}
			
			else {
				sb.append(TheWord_Letter);
			}
			
				}
		
		TheWord=sb.toString();
		if(!TheWord.contains(Tryout)) {
			setTrials_left();
		}
		if(!Letter_Guess)
			{System.out.println("Wrong Guess.....");return;}
		
		if(checkCompatbility(TheWord)) {
			
			System.out.println("The Game Has finished With success !!!,the word was : "+TheWord);
			
			setGame_WIN_Status(true);
			
			
			return;}
		
		System.out.println("You are at : "+TheWord);
		
		}
	
	
	public boolean isGame_WIN_Status() {
		return Game_WIN_Status;
	}
	
	
	public void setGame_WIN_Status(boolean game_WIN_Status) {
		Game_WIN_Status = game_WIN_Status;
	}
	public int getTrials_left() {
		return Trials_left;
	}

	public void setTrials_left() {
		Trials_left -=1;
	}
	public String getQuestionHint() {
		return Questionhint+"";
	}

	
	public void setQuestionhint(QuestionHint questionhint) {
		Questionhint = questionhint;
	}
		
			
		

			
		
	

}
