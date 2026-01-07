import java.util.Scanner;

public class HangmanTest {

    public static void main(String[] args)
    {
        GameSession gameSession = new GameSession();
        Game game = gameSession.getGame();
        QuestionHint q1 = new QuestionHint(game.getWordToGuess());
        q1.start();

        try(Scanner sc = new Scanner(System.in)){


            while(game.getGameStatus()== GameStatus.PENDING){
                System.out.print("Enter only A letter (Not a chain of letters) : ");
                String word = sc.next();


                char letter = Character.valueOf(word.toCharArray()[0]);

                System.out.println(game.drawGame(letter));

                if(game.getCurrentFalseTrials() == 3)
                {
                    System.out.println("Your Hint is : "+q1.getTheHint());
                }







            }
            if(game.getGameStatus() == GameStatus.WON) {
                System.out.println(game.getPlayerCurrentGuess());
                System.out.println("You have Won !!!");
            }
            else{
                System.out.println("You have Lost !!!");
                System.out.println("your word was : "+game.getWordToGuess());
            }
        }catch(Exception ex)
        {
            System.out.println("An error occured "+ex);
        }




    }


}
