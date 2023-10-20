public class Board
{

    // "clever" hint about the Parcheesi board:
    // If you divide the number of a square by 17 and look at the remainder, you can get important
    // information about that square. For example...
    //      if the remainder is zero, this is a safe space.
    //      if the remainder is seven, this is a safe space.
    //      if the remainder is twelve, this is a safe space.
    //      if the remainder is zero, this is the starting position for one of the players (int)(squareNum/17)...
    //      if the remainder is twelve, this is the start of a home row for one of the players (I'll let you figure out
    //             which one.)

    private Space[] mainLoop;
    private Space[][] safePaths; // see note, below.
    private int[] numChipsInStartingPointsPerPlayer = {4,4,4,4};
    private int[] numChipsInHomePerPlayer = {0,0,0,0};


    public Board()
    {
        mainLoop = new Space[68];
        safePaths = new Space[4][6];  // this makes four 6-element arrays of Spaces. Technically, this is a 2-d array we
                                      // haven't covered yet. Just think of it as an array of arrays.
            safePaths[0] = new Space[6];
            safePaths[1] = new Space[6];
            safePaths[2] = new Space[6];
            safePaths[3] = new Space[6];
        // ------------------------------
        // TODO: initialize all these spaces in both the main loop and the safe paths. Make sure you set the appropriate squares to safe!
        for (int i = 0; i <= mainLoop.length - 1; i++){
            mainLoop[i] = new Space();
            if (i % 17 == 0 || i % 17 == 7 || i % 17 == 12){
                mainLoop[i].setSafe(true);
                if (i % 17 == 7){;
                }
            } else {
                mainLoop[i].setSafe(false);
            }
        }
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 6; j++){
                safePaths[i][j] = new Space();
                safePaths[i][j].setSafe(true);
            }
        }

        // ------------------------------

    }

    private String fillStart(int playerNumber){
        String result = "";
        for (int i = numChipsInStartingPointsPerPlayer[playerNumber]; i > 0; i--){
            result += playerNumber;
        }
        for (int i = 4 - numChipsInStartingPointsPerPlayer[playerNumber]; i > 0; i--){
            result += " ";
        }
        return result;
    }

    private String fillHome(int playerNumber){
        String result = "";
        for (int i = numChipsInHomePerPlayer[playerNumber]; i > 0; i--){
            result += playerNumber;
        }
        for (int i = 4 - numChipsInHomePerPlayer[playerNumber]; i > 0; i--){
            result += " ";
        }
        return result;
    }


    public String toString()
    {
        String result = "";
        // -------------------------------
        // TODO: in a loop, keep appending information to "result" so that result winds up being a string that you can print to see the whole board.
        for (int i = 0; i <= mainLoop.length - 1; i++){
            System.out.print(i + "\t");
            System.out.print(mainLoop[i]);
            int piece = (i/17);
            int piece2;
            if (piece != 3){
                piece2 = piece + 1;
            } else {
                piece2 = 0;
            }
            if (i % 17 == 0){
                System.out.println("<-- Player " + piece + " start. <" + fillStart(piece) + ">");
            } else if (i % 17 == 12){
                for (int j = 0; j < 6; j++) {
                    System.out.print(safePaths[piece][j]);
                }
                System.out.print("<" + fillHome(piece) + "> Safe Path for " + piece2);
                System.out.println();
            } else if ((i + 1) % 17 == 12){
                System.out.println("  -5   -4   -3   -2   -1   0   Home");
            } else {
                System.out.println();
            }

        }

        // suggestion: start by just printing the row numbers, a tab, and the squares themselves.
        // then you can get fancy by printing information about the various players' starting positions.
        // then you can get fancy by adding in the safe rows to the goal for the various players.

        // -------------------------------

        return result;
    }

}
