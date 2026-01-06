import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class GameSession {

    private static int counter = 0 ;
    private  static String[] listOfWords ;



    private int idGameSession ;




    private Game game  ;



    // Initialize All Words At Once ...
    static {

        try(BufferedReader reader
                    = new BufferedReader(
                            new FileReader("src\\main\\resources\\words.txt")
                                        )
            )
        {
            listOfWords = reader.lines()
                    .filter(word -> word.length() >= 4)
                    .toArray(size -> new String[size]);



        }catch(Exception ex)
        {
            System.out.println("An exception occured !!!");
        }


    }


    // Constructor
    public GameSession()
    {
        idGameSession = ++counter ;
        String wordToGuess = listOfWords[new Random().nextInt(0,listOfWords.length)];
        game = new Game(wordToGuess);







    }

    public Game getGame() {
        return game;
    }


}




// Class game
class Game {

    private static int counter = 0 ;
    private int idGame ;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    private String wordToGuess ;

    public StringBuilder getPlayerCurrentGuess() {
        return playerCurrentGuess;
    }

    public void setPlayerCurrentGuess(StringBuilder playerCurrentGuess) {
        this.playerCurrentGuess = playerCurrentGuess;
    }

    private StringBuilder playerCurrentGuess;
    private  int currentFalseTrials =  0 ;
    private static int maxFalseTrials = 5 ;
    private GameStatus gameStatus ;

    public Game(String wordToGuess)
    {
        idGame = ++counter ;
        this.wordToGuess = wordToGuess ;
        playerCurrentGuess = new StringBuilder();
        gameStatus = GameStatus.PENDING;

        setPlayerCurrentGuess('?');
    }


    public int getCurrentFalseTrials() {
        return currentFalseTrials;
    }

    public void setCurrentFalseTrials(int currentFalseTrials) {
        this.currentFalseTrials = currentFalseTrials;
    }

    public int getIdGame() {
        return idGame;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    private StringBuilder setPlayerCurrentGuess(char letter)
    {   int n  = wordToGuess.length();

        if(letter == '?') {

            // Initialize current Player Word to empty like this (-----)
            for (int i = 0; i < n; i++) {
                    playerCurrentGuess.append("-");

            }

            return playerCurrentGuess ;
        }
        // check for new Attempts ....
        for (int i = 0; i < n; i++) {

            if(letter == wordToGuess.charAt(i))
                playerCurrentGuess.setCharAt(i,letter);
        }



     return playerCurrentGuess ;





    }

    private void trackGameStatus(char letter)
    {   // Create a new String so to not confuse it with the one in .equals(...)

        String playerGuessBefore
                = new String(playerCurrentGuess.toString());

        String playerGuessAfter
                = new String(setPlayerCurrentGuess(letter).toString());

        if (wordToGuess.equals(playerGuessAfter))
        {
            gameStatus = GameStatus.WON;
        }
        else if(playerGuessBefore.equals(playerGuessAfter))
        {
            currentFalseTrials++;

            if(currentFalseTrials == maxFalseTrials)
            {
                gameStatus = GameStatus.LOST;
            }


        }


    }

    public  String drawGame(char letter)
    {
        trackGameStatus(letter);


        return Game.Hangman(currentFalseTrials) +" \n \n Current Word : "+playerCurrentGuess;

    }






    private static String Hangman(int falseAttempts) {

        return switch (falseAttempts) {
            case 0 -> "";

            case 1 -> "|¬ . ¬|";

            case 2 -> "|¬ . ¬|"+"\n"+
                    "   | /" ;

            case 3 -> "|¬ . ¬|"+"\n"+
                    " \\ | /" ;

            case 4 ->"|¬ . ¬|"+"\n"+
                    " \\ | /"+"\n"+
                    "   |"+"\n"+
                    "     \\";

            case 5 ->"\\x . x\\"+"\n"+
                    "   | "+"\n"+
                    "  /|\\"+"\n"+
                    "   |  "+"\n"+
                    "  / \\";

            default -> "?";

        };
    }

}


enum GameStatus{

    PENDING ,
    LOST ,
    WON
}





