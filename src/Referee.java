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
            int currentSpace;
            int currentSpace2;
            int move1st;
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(myBoard);
            die1 = (int) (Math.random() * 6) + 1;
            die2 = (int) (Math.random() * 6) + 1;
            System.out.println("Player: " + currentPlayer + " you've rolled:\n" + "Die 1: " + die1 + "\n" + "Die 2: " + die2);
            if (die1 == 5 || die2 == 5) {
                if ((die1 == 5 && die2 == 5) && (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) >= 2)) {
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
                            System.out.println("For the second dice, where is the piece you want to move?");
                            int move = scanner.nextInt();
                            if (die1 == 5) {
                                move2nd = 2;
                            } else if (die2 == 5){
                                move2nd = 1;
                            }
                            System.out.println("The piece on " + move + " has moved " + getDice(move2nd) + " spaces to " + (move + getDice(move2nd)));
                        }
                    }
                } else {
                    System.out.println("Do you want to take a single piece out?");
                    String answer3 = scanner.next();
                    if (answer3.equals("yes")) {
                        myBoard.setSpaceWithPiece(currentPlayer, 1);
                        myBoard.setNCISPPP(currentPlayer, 1);
                        System.out.println(myBoard);
                        System.out.println("For the second dice, where is the piece you want to move?");
                        int move = scanner.nextInt();
                        if (die1 == 5) {
                            move2nd = 2;
                        } else if (die2 == 5){
                            move2nd = 1;
                        }
                        System.out.println("The piece on " + move + " has moved " + getDice(move2nd) + " spaces to " + (move + getDice(move2nd)));
                        if (die1 != die2) {
                            if (currentPlayer != 3) {
                                currentPlayer = currentPlayer + 1;
                            } else {
                                currentPlayer = 0;
                            }
                        }
                        if (true) continue;
                    } else if (answer3.equals("no")){
                        System.out.println("Which do you want to use to move?");
                        move1st = scanner.nextInt();
                        System.out.println("Which space is the piece you want to move on?");
                        currentSpace = scanner.nextInt();
                        System.out.println("The piece on " + currentSpace + " has moved " + getDice(move1st) + " spaces to " + (currentSpace + getDice(move1st)));
                        System.out.println("For the second dice, on which space is the piece you want to use?");
                        currentSpace2 = scanner.nextInt();
                        if (move1st == 1) {
                            move2nd = 2;
                        } else {
                            move2nd = 1;
                        }
                        System.out.println("The piece on " + currentSpace2 + " has moved " + getDice(move2nd) + " spaces to " + (currentSpace2 + getDice(move2nd)));
                        if (die1 != die2) {
                            if (currentPlayer != 3) {
                                currentPlayer = currentPlayer + 1;
                            } else {
                                currentPlayer = 0;
                            }
                        }
                        if(true) continue;
                    }
                }
                if (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) != 4) {
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
                }
            } else if (myBoard.getNumChipsInStartingPointsPerPLayer(currentPlayer) != 4){
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
            }

            if (die1 != die2) {
                if (currentPlayer != 3) {
                    currentPlayer = currentPlayer + 1;
                } else {
                    currentPlayer = 0;
                }
            }
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

}
