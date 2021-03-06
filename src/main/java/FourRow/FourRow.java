package src.main.java.FourRow;

import src.main.java.main.Board;

import java.util.Random;

public class FourRow {

    public Board board;
    private int player;
    CheckRulesFourRow rules;
    Random randomMove = new Random();

    public FourRow(int player) {
        this.board = new Board(7, 6);

        this.player = player;
        rules = new CheckRulesFourRow(board, player);

    }

    public boolean makeMove(int player, int col) {
        if (rules.checkLegalMove(col)) {
            board.updateBoardFourRow(player, col);
            return true;
        } else {
            return false;
        }
    }

    public boolean checkWinner(int player) {
        for (int row = 0; row < board.board.length; row++) {
            for (int col = 0; col < board.board[0].length; col++) {
                if (in_a_row_n_east(player, row, col)) {
                    return true;
                }
                if (in_a_row_n_south(player, row, col)) {
                    return true;
                }
                if (in_a_row_n_northeast(player, row, col)) {
                    return true;
                }
                if (in_a_row_n_southeast(player, row, col)) {
                    return true;
                }
            }
        }
        return false;

    }


    public boolean in_a_row_n_east(int player, int r_start, int c_start) {
        int h = board.board.length;
        int w = board.board[0].length;
        if (r_start < 0 || r_start > h - 1) {
            return false;
        }
        if (c_start < 0 || c_start + (4 - 1) > w - 1) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (board.board[r_start][c_start + i] != player) {
                return false;
            }
        }
        return true;
    }


    public boolean in_a_row_n_south(int player, int r_start, int c_start) {
        int h = board.board.length;
        int w = board.board[0].length;

        if (r_start < 0 || r_start + (4 - 1) > h - 1) {
            return false;
        }
        if (c_start < 0 || c_start > w - 1) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (board.board[r_start + i][c_start] != player) {
                return false;
            }
        }
        return true;
    }


    public boolean in_a_row_n_northeast(int player, int r_start, int c_start) {
        int h = board.board.length;
        int w = board.board[0].length;

        if (r_start - (4 - 1) < 0 || r_start > h - 1) {
            return false;
        }
        if (c_start < 0 || c_start + (4 - 1) > w - 1) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (board.board[r_start - i][c_start + i] != player) {
                return false;
            }
        }
        return true;
    }


    public boolean in_a_row_n_southeast(int player, int r_start, int c_start) {
        int h = board.board.length;
        int w = board.board[0].length;

        if (r_start < 0 || r_start + (4 - 1) > h - 1) {
            return false;
        }
        if (c_start < 0 || c_start + (4 - 1) > w - 1) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (board.board[r_start + i][c_start + i] != player) {
                return false;
            }
        }
        return true;
    }

    public void setBoardArray(int[][] board) {
        this.board.board = board;
    }

    public int getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public void AIMove(int player) {

        makeMove(player, FourRowAI.AiMove(getBoard(),player));

    }

    public static int getOpponent(int player) {
            if (player == 1) {
                return 2;
            }
            else {return 1;}
    }

    public int[][] getBoardArray() {
            return board.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean boardFull() {
        for (int row = 0; row < getBoardArray().length; row++) {
            for (int col = 0; col < getBoardArray()[0].length; col++) {
                if (getBoardArray()[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
