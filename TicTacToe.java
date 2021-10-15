import java.util.Random;

public class TicTacToe {

    private Random randomMove;
    private Board board;
    public int player;

    public TicTacToe() {
        randomMove = new Random();
        board = new Board();
        this.player = 1;
    }

    public void aiMove(int ai) {
        int x = randomMove.nextInt(board.getSize());
        int y = randomMove.nextInt(board.getSize());

        if (CheckRules.checkLegalMove(Board.getBoard(), x, y) == true) {
            board.updateBoard(ai, x, y);
        } else {
            aiMove(ai);
        }
    }

    public boolean makeMove(int x, int y) {
        if (CheckRules.checkLegalMove(Board.getBoard(), x, y)) {
            board.updateBoard(player, x, y);
            return true;
        } else {
            System.out.println("illegal move");
            return false;
        }
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

}