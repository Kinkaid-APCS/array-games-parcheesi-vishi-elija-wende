import java.sql.SQLOutput;
import java.util.Scanner;

public class Referee
{
    private Board myBoard;
    private int die1, die2;
    private int move2nd;
    private int currentPlayer = 0;
    Scanner scanner = new Scanner(System.in);

    public Referee()
    {
        myBoard = new Board();
    }

    public void playGame()
    {
        while (true) {

            int currentSpace;
            int currentSpace2;
            int move1st;
            System.out.println(myBoard);
            die1 = (int) (Math.random() * 6) + 1;
            die2 = (int) (Math.random() * 6) + 1;
            System.out.println("You've rolled:\n" + "Die 1: " + die1 + "\n" + "Die 2: " + die2);
            if (die1 == 5 || die2 == 5) {
                if (die1 == 5 && die2 == 5) {
                    System.out.println("Do you want to take two pieces out?");
                    String answer = scanner.next();
                    if (answer == "yes") {
                        //put two pieces on the board
                        myBoard.setNCISPPP(currentPlayer, 2);
                    }
                }
            }
            System.out.println("Which do you want to use to move?");
            move1st = scanner.nextInt();
            System.out.println("Which space is the piece you want to move on?");
            currentSpace = scanner.nextInt();
            System.out.println("The piece on " + currentSpace + " has moved " + getDice(move1st) + " spaces to " + (currentSpace + getDice(move1st)));
            System.out.println("For the second dice, on which space is the piece you want to use?");
            currentSpace2 = scanner.nextInt();
            //        move2nd = setMove2nd(move1st);
            if (move1st == 1) {
                move2nd = 2;
            } else {
                move2nd = 1;
            }
            System.out.println("The piece on " + currentSpace2 + " has moved " + getDice(move2nd) + " spaces to " + (currentSpace2 + getDice(move2nd)));





            if (currentPlayer != 3){
                currentPlayer = currentPlayer + 1;
            } else {
                currentPlayer = 0;
            }

            myBoard.setNCISPPP(currentPlayer, 2);

        }
    }

    public int getDice(int move){
        if (move == 1){
            return die1;
        } else if (move == 2){
            return die2;
        }
        return 0;
    }

//    public int setMove2nd(int move){
//        if (move == 1){
//            return die2;
//        } else{
//            return die1;
//        }
//    }
}
