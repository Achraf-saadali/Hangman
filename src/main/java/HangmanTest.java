import java.util.Scanner;

public class HangmanTest {

    public static void main(String[] args)
    {
        GameSession gameSession = new GameSession();

        try(Scanner sc = new Scanner(System.in)){
            Game game = gameSession.getGame();

            while(game.getGameStatus()== GameStatus.PENDING){
                System.out.print("Enter only A letter (Not a chain of letters) : ");
                String word = sc.next();


                char letter = Character.valueOf(word.toCharArray()[0]);

                System.out.println(game.drawGame(letter));







            }
            if(game.getGameStatus() == GameStatus.WON)
                System.out.println("You have Won !!!");
            else{
                System.out.println("You have Lost !!!");
            }
        }




    }


}
