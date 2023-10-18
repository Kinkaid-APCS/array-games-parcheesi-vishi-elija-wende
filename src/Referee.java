public class Referee
{
    private Board myBoard;

    public Referee()
    {
        myBoard = new Board();
    }

    public void playGame()
    {
        System.out.println(myBoard);
    }
}
