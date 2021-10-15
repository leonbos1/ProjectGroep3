import java.lang.Random;

public class TicTacToe {

    private Random randomMove;
    private Board board;
    public int player;
    public int otherPlayer;

    public TicTacToe() {
        randomMove = new Random();
        board = new Board();
        this.player = 1;
        this.otherPlayer = -1;
    }

    public void aiMove() {
        int x = randomMove.nextInt(board.getSize());
        int y = randomMove.nextInt(board.getSize());

        if (CheckRules.checkLegalMoves(board, x, y) == true) {
            board.updateBoard(board.getPlayer(), x, y);
            if(CheckRules.checkWinner(board, player)) {
                System.out.println("Het spel is voorbij, de winnaar is speler " + player);
            }
            if(CheckRules.checkBoardFull(board)) {
                System.out.println("Spel is voorbij, er is geen winnaar");
            }
            switchPlayer = player;
            player = otherPlayer;
            otherPlayer = switchPlayer;
        }
        else {
            aiMove();

        }

    }

    public void makeMove(int x, int y) {

        if (CheckRules.checkLegalMoves(board, x, y) == true) {
            board.updateBoard(board.getPlayer(), x, y);
            switchPlayer = player;
            player = otherPlayer;
            otherPlayer = switchPlayer;
            if(CheckRules.checkWinner(board, player)) {
                System.out.println("Het spel is voorbij, de winnaar is speler " + player);
            }
            if(CheckRules.checkBoardFull(board)) {
                System.out.println("Spel is voorbij, er is geen winnaar");
            }
        }
        else {
            System.out.println("illegal move");
            makeMove();
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