import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
//            try {
//                Thread.sleep(900);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
            System.out.println(myBoard);
            die1 = (int) (Math.random() * 6) + 1;
            die2 = (int) (Math.random() * 6) + 1;
            System.out.println("Player: " + currentPlayer + " you've rolled:\n" + "Die 1: " + die1 + "\n" + "Die 2: " + die2);
            if (die1 == 5 || die2 == 5) {
                if ((die1 == 5 && die2 == 5) && (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) >= 2) && myBoard.getNumPieces(myBoard.getWhichStartSpace(currentPlayer)) <= 1) {
                    System.out.println("Do you want to take two pieces out?");
                    String answer = scanner.next();
                    if (answer.equals("yes")) {
                        //put two pieces on the board
                        myBoard.setSpaceWithPiece(currentPlayer, 2);
                        myBoard.setNCISPPP(currentPlayer, 2);
                        System.out.println(myBoard);
                        if(true) continue;
                    } else if (answer.equals("no")){
                        System.out.println("Do you want to take a single piece out?");
                        String answer2 = scanner.next();
                        if (answer2.equals("yes")) {
                            myBoard.setSpaceWithPiece(currentPlayer, 1);
                            myBoard.setNCISPPP(currentPlayer, 1);
                            System.out.println(myBoard);
                            playOne();
                        }
                    }
                } else if (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) >= 1 && myBoard.getNumPieces(myBoard.getWhichStartSpace(currentPlayer)) <= 1) {
                    System.out.println("Do you want to take a single piece out?");
                    String answer3 = scanner.next();
                    if (answer3.equals("yes")) {
                        myBoard.setSpaceWithPiece(currentPlayer, 1);
                        myBoard.setNCISPPP(currentPlayer, 1);
                        System.out.println(myBoard);
                        playOne();
                        changePlayer(die1, die2);
                        if (true) continue;
                    } else if (answer3.equals("no")){
                        playNormal();
                        changePlayer(die1, die2);
                        if(true) continue;
                    }
                }
                if (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) != 4) {
                    playNormal();
                }
            } else if (false /*myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) != 4 */){
                playNormal();
            }
            changePlayer(die1, die2);
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

    private void changePlayer(int dieA,int dieB){
        if (dieA != dieB) {
            if (currentPlayer != 3) {
                currentPlayer = currentPlayer + 1;
            } else {
                currentPlayer = 0;
            }
        }
    }

    private void playNormal(){
        System.out.println("Which dice do you want to use to move?");
        int move1st = scanner.nextInt();
        System.out.println("Which space is the piece you want to move on?");
        int currentSpace = scanner.nextInt();
        hasPiece(currentSpace, 0);
        checkCanMove(currentSpace, (currentSpace + getDice(move1st)), 0);
        System.out.println("The piece on " + currentSpace + " has moved " + getDice(move1st) + " spaces to " + (currentSpace + getDice(move1st)));
        myBoard.movePiece(currentSpace, getDice(move1st), currentPlayer);

        System.out.println("For the second dice, on which space is the piece you want to use?");
        int currentSpace2 = scanner.nextInt();
        hasPiece(currentSpace2, 1);
        if (move1st == 1) {
            move2nd = 2;
            die1 = 0;
        } else {
            move2nd = 1;
            die2 = 0;
        }
        checkCanMove(currentSpace2, (currentSpace2 + getDice(move2nd)), 1);
        System.out.println("The piece on " + currentSpace2 + " has moved " + getDice(move2nd) + " spaces to " + (currentSpace2 + getDice(move2nd)));
        myBoard.movePiece(currentSpace2, getDice(move2nd), currentPlayer);
    }

    private void playOne(){
        System.out.println("For the second dice, on which space is the piece you want to use??");
        int move = scanner.nextInt();
        hasPiece(move, 1);
        if (die1 == 5) {
            move2nd = 2;
        } else if (die2 == 5){
            move2nd = 1;
        }
        checkCanMove(move, (move + getDice(move2nd)), 1 );
        System.out.println("The piece on " + move + " has moved " + getDice(move2nd) + " spaces to " + (move + getDice(move2nd)));
        myBoard.movePiece(move, getDice(move2nd), currentPlayer);
    }

    private void loopBoard(){
        // check if moving how many spaces from spot goes over 67 and then loops it around the board
    }

    private void hasPiece(int currSpace, int j){
        System.out.println(myBoard.checkSpaceFrom(currSpace, currentPlayer));
        boolean hasPiece = myBoard.checkSpaceFrom(currSpace, currentPlayer);
        if (!hasPiece){
            if (j == 0) {
                playNormal();
            } else if (j == 1) {
                playOne();
            }
        }
    }

    private void checkCanMove(int currSpace, int endSpace, int j){
        System.out.println(myBoard.checkSpacesBetween(currSpace, endSpace));
        boolean canMove = myBoard.checkSpacesBetween(currSpace, endSpace);
        if (!canMove){
            if (j == 0) {
                playNormal();
            } else if (j == 1) {
                playOne();
            }
        }
    }
}
