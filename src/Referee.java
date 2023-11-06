import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Referee
{
    private Board myBoard;
    private int die1, die2;
    private int move2nd, move3rd;
    private int currentPlayer = 0;
    Scanner scanner = new Scanner(System.in);


    public boolean gameGoing = true;
    public Referee()
    {
        myBoard = new Board();
    }


    public void playGame()
    {
        while (gameGoing) {
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
                            if (true) continue;
                        } else if (answer.equals("no")) {
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
                        } else if (answer3.equals("no")) {
                            playNormal();
                            changePlayer(die1, die2);
                            if (true) continue;
                        }
                    }
                    if (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) != 4) {
                        playNormal();
                    }
                } else if (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) != 4) {
                    playNormal();
                }
            }
            if (myBoard.getNumChipsInHomePerPlayer(currentPlayer) == 4){
                System.out.println("Player: " + currentPlayer + " wins the game!!!");
                gameGoing = false;
            }
            changePlayer(die1, die2);
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
        System.out.println("Which die do you want to use to move? Input 1 for Die 1 and 2 for Die 2.");
        int move1st = scanner.nextInt();
        System.out.println("Which space is the piece you want to move on?");
        int currentSpace = scanner.nextInt();
        hasPiece(currentSpace, 0);
        if (currentSpace + getDice(move2nd) > 67){
            move3rd = currentSpace + getDice(move2nd) - 67;
        } else{
            move3rd = currentSpace + getDice(move2nd);
        }
        checkCanMove(currentSpace, move3rd, 0);
        System.out.println("The piece on " + currentSpace + " has moved " + getDice(move1st) + " spaces to " + (currentSpace + getDice(move1st)));
        myBoard.movePiece(currentSpace, getDice(move1st), currentPlayer, false);


        System.out.println("For the second dice, on which space is the piece you want to move?");
        int currentSpace2 = scanner.nextInt();
        hasPiece(currentSpace2, 1);
        if (move1st == 1) {
            move2nd = 2;
            //die1 = 0;
        } else {
            move2nd = 1;
            //die2 = 0;
        }
        if (currentSpace2 + getDice(move2nd) > 67){
            move3rd = currentSpace2 + getDice(move2nd) - 67;
        } else{
            move3rd = currentSpace2 + getDice(move2nd);
        }
        checkCanMove(currentSpace2, move3rd, 1);
        System.out.println("The piece on " + currentSpace2 + " has moved " + getDice(move2nd) + " spaces to " + (currentSpace2 + getDice(move2nd)));
        myBoard.movePiece(currentSpace2, getDice(move2nd), currentPlayer, false);
    }


    private void playOne(){
        System.out.println("For the second dice, on which space is the piece you want to use?");
        int move = scanner.nextInt();
        hasPiece(move, 1);
        if (die1 == 5) {
            move2nd = 2;
        } else if (die2 == 5){
            move2nd = 1;
        }
        if (move + getDice(move2nd) > 67){
            move3rd = move + getDice(move2nd) - 67;
        } else{
            move3rd = move + getDice(move2nd);
        }
        checkCanMove(move, move3rd, 1 );
        System.out.println("The piece on " + move + " has moved " + getDice(move2nd) + " spaces to " + (move + getDice(move2nd)));
        myBoard.movePiece(move, getDice(move2nd), currentPlayer, false);
    }


    private void hasPiece(int currSpace, int j){
        if (currSpace >= 0) {
            System.out.println(myBoard.checkSpaceFrom(currSpace, currentPlayer));
            boolean hasPiece = myBoard.checkSpaceFrom(currSpace, currentPlayer);
            if (!hasPiece) {
                if (j == 0) {
                    playNormal();
                } else if (j == 1) {
                    playOne();
                }
            }
        } else {
            System.out.println("true");
        }
    }


    private void checkCanMove(int currSpace, int endSpace, int j) {
        if (endSpace < myBoard.getWhichHomeSpace(currentPlayer)) {
            System.out.println(myBoard.checkSpacesBetween(currSpace + 1, endSpace));
            boolean canMove = myBoard.checkSpacesBetween(currSpace + 1, endSpace);
            if (!canMove) {
                if (j == 0) {
                    playNormal();
                } else if (j == 1) {
                    playOne();
                }
            }
        } else {
            System.out.println(myBoard.checkSpacesBetween(currSpace + 1, myBoard.getWhichHomeSpace(currentPlayer)));
            boolean canMoveSpecialCase = myBoard.checkSpacesBetween(currSpace + 1, myBoard.getWhichHomeSpace(currentPlayer));
            if (!canMoveSpecialCase) {
                if (j == 0) {
                    playNormal();
                } else if (j == 1) {
                    playOne();
                }
            }
        }
    }

}
