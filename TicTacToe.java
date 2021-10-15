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

    public int aiMove(int ai) {
        int x = randomMove.nextInt(board.getSize());
        int y = randomMove.nextInt(board.getSize());

        if (CheckRules.checkLegalMove(getBoardArray(), x, y) == true) {
            board.updateBoard(ai, x, y);
            return x*board.getSize()+y+1;
        } else {
            return aiMove(ai);
        }
    }

    public boolean makeMove(int x, int y) {
        if (CheckRules.checkLegalMove(getBoardArray(), x, y)) {
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

    public Board getBoard() {
        return board;
    }

    public int[][] getBoardArray() {
        return board.getBoard();
    }
}