package src.main.java.FourRow;

import src.main.java.main.Board;
import src.main.java.reversi.Reversi;

public class FourRowAI {

    private static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                newBoard[row][col] = board[row][col];
            }
        }
        return newBoard;
    }

    public static int AiMove(Board board, int player) {
        FourRow fourRow = new FourRow(player);
        fourRow.setBoardArray(board.getBoard());

        for (int col = 0; col < 7; col++) {
            FourRow copyFourRow = new FourRow(player);
            copyFourRow.setBoardArray(copyBoard(board.getBoard()));
            copyFourRow.makeMove(FourRow.getOpponent(player), col);

            if (copyFourRow.checkWinner(player)) {
                return col;
            }
        }

        for (int col = 0; col < 7; col++) {
            FourRow copyFourRow = new FourRow(player);
            copyFourRow.setBoardArray(copyBoard(board.getBoard()));
            copyFourRow.makeMove(FourRow.getOpponent(player), col);

            if (copyFourRow.checkWinner(FourRow.getOpponent(player))) {
                return col;
            }
        }

        for (int col = 0; col < 7; col++) {
            CheckRulesFourRow rules = new CheckRulesFourRow(fourRow.getBoard(), player);
            if (rules.checkLegalMove(col)) {
                return col;
            }
        }
        return 0;

    }
}
