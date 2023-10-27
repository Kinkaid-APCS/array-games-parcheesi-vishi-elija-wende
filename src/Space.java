public class Space
{

    /**
     * Each space can have be empty, or it can have one or two of a single player's pieces. (I.e., two different players
     * cannot share a space. Some spaces are considered "safe" - these show up with an asterisk next to them in our game.
     * They correspond to the spaces with circles on them and the home rows on the original board game.
     */

    private int whoIsHere; // which player, if any has pieces on this space.
    private int numPieces; // how many pieces, {0, 1, or 2} are on this space
    private boolean isSafe; // whether this is a safety space.


    public Space()
    {
        whoIsHere = 4;
        numPieces = 0;
        isSafe = false;
    }

    public void setWhoIsHere(int whoIsHere) {
        this.whoIsHere = whoIsHere;
    }

    public int getWhoIsHere() {
        return this.whoIsHere;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    public int getNumPieces() {
        return this.numPieces;
    }
    public boolean isSafe()
    {
        return isSafe;
    }

    public void setSafe(boolean safe)
    {
        isSafe = safe;
    }

    /**
     * generates a string describing this space. It will lead with an asterisk if it is safe, or a space if not. The
     * space itself is drawn as two characters, surrounded by "pipes" ("|") - spaces, if the space is considered empty,
     * or one or more of the number of the player if there are pieces on this board.
     * For example:
     * " |  |" - an empty, unsafe space.
     * "*|3 |" - a safe space with one of player 3's pieces on it.
     * " |00|" - an unsafe space with two of player 0's pieces on it.
     * @return - a string describing this space.
     */
    @Override
    public String toString()
    {
        String result;
        if (isSafe())
            result ="*|";
        else
            result = " |";
        for (int i = 0; i<2; i++)
            if (i<numPieces)
                result+= whoIsHere;
            else
                result+= " ";
        result+="|";

        return result;
    }
}
