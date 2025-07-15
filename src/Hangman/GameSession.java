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
		setTheWordFormat(WordToGuess.length());}
		
	
	
	
	private void setTheWordFormat(int length) {
		 StringBuilder SB=new StringBuilder();
		for(int i=0;i<length;i++) {
			SB.append("-");
			
		}
		TheWord=SB.toString();
		
	}
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	
	public String getTheWord() {
		return TheWord;
	}
	
	
	public void setTheWord(String word) {
		 TheWord=word;
	}
	
	 private boolean checkCompatbility(String PlayerWordGuess) {
		
		 return PlayerWordGuess.equals(WordToGuess); }
	 
	 
	 
	
	
	public boolean isGame_WIN_Status() {
		return Game_WIN_Status;
	}
	 
	 public void changeTheWord(String Input) {
		 StringBuilder BuildGuessedWord= new StringBuilder(); 
		  boolean Guess_Is_Right=false;
		 if(Input.length()>1 || Input.length()==0) {

               if(checkCompatbility(Input)) {
            	   Game_WIN_Status=true;
            	   Guess_Is_Right=true;
            	   System.out.println("You have won");
            	   return;
              
               }
              
		 
		 }
		 String GuessedWord=getTheWord();
		 BuildGuessedWord.append(GuessedWord);
		 if(GuessedWord.contains(Input))
		 {Guess_Is_Right=true;System.out.println("Already Guessed Try again......");
		 return;
		 }
		 
		 if(getWordToGuess().contains(Input)) {
			 Guess_Is_Right=true;
			 
			 char InputChar=Input.charAt(0);
			 BuildGuessedWord.setCharAt(getWordToGuess().indexOf(Input), InputChar);
			 setTheWord(BuildGuessedWord.toString());
			 
			 if(checkCompatbility(getTheWord())) {
				 Game_WIN_Status=true;
				 System.out.println("You have won");
				 return;
			 }
			 System.out.println("Right Guess... continue");
			 return;
			 
		 }
		 
		 
		 
		 if(!Guess_Is_Right) {
			 setTrials_left(); 
			 System.err.println("Wrong Guess.... Try again");
			 System.out.println(getTrials_left());
			 
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		
		 
		 
		 
		 
		 
		 
		 
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
	public String getQuestionHintResponse() {
		return Questionhint+"";
	}

	
	public void setQuestionhint(QuestionHint questionhint) {
		Questionhint = questionhint;
	}
	
	public QuestionHint getQuestionHint() {
		return Questionhint;
	}
		
			
		

			
		
	

}
