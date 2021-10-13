import java.lang.Random;

public class TicTacToe {

    private Random randomMove;
    private Board board;
    public int player;

    public TicTacToe() {
        randomMove = new Random();
        board = new Board();
        this.player = 1;
    }

    public void aiMove() {
        int x = randomMove.nextInt(board.getSize());
        int y = randomMove.nextInt(board.getSize());

        if (CheckRules.checkLegalMoves(board, x, y) == true) {
            board.updateBoard(board.getPlayer(), x, y);
        }
        else {
            aiMove();
        }

    }

    public void makeMove(int x, int y) {

        if (CheckRules.checkLegalMoves(board, x, y) == true) {
            board.updateBoard(board.getPlayer(), x, y);
        }
        else {
            System.out.println("illegal move");
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

}