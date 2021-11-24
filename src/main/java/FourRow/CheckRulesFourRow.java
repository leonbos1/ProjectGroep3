package src.main.java.FourRow;

import src.main.java.main.Board;

public class CheckRulesFourRow {

    Board board;
    int player;

    public CheckRulesFourRow(Board board, int player) {
        this.board = board;
        this.player = player;
    }

    public boolean checkLegalMove(int col) {

        if (col > board.board[0].length) {return false;}

        else if (col < 0) {return false;}

        else if (isFull(col)) {return false;}

        else {return true;}
    }
    public boolean isFull(int col) {
        for (int i = 0; i < 6; i++) {
            if (board.board[i][col] == 0 ) {
                return false;
            }
        }
        return true;
    }

}
