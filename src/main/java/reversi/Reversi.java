package src.main.java.reversi;

import src.main.java.main.Board;

public class Reversi {

    Board board;

    public Reversi(Board board) {
        this.board = board;
        board.board[3][3] = 1;
        board.board[4][4] = 1;
        board.board[4][3] = 2;
        board.board[3][4] = 2;
    }

    public void makeMove(int row, int col, int player) {
        if (src.main.java.reversi.CheckRulesReversi.checkLegalMove(board.board, row, col, player)) {
            System.out.printf("Speler %d deed een move op %d, %d\n",player,row,col);
            flipStones(row, col, player);
        }
        else {
            System.out.println("Illegale zet door "+player);
        }
    }

    public void AIMove() {
        int[] result = ReversiAI.AIMove(board.board);

        makeMove(result[0],result[1],2 );
    }

    private void flipStones(int row, int col, int player) {
        if (CheckRulesReversi.checkLegalMove(board.board,row, col, player)) {board.board[row][col] = player;}

        if (CheckRulesReversi.checkLegalUp(board.board,row, col, player)) {flipStonesUp(row, col, player);}

        if (CheckRulesReversi.checkLegalDown(board.board,row, col, player)) {flipStonesDown(row, col, player);}

        if (CheckRulesReversi.checkLegalLeft(board.board,row,col, player)) {flipStonesLeft(row, col, player);}

        if (CheckRulesReversi.checkLegalRight(board.board,row, col, player)) {flipStonesRight(row, col, player);}

        if (CheckRulesReversi.checkLegalLeftUp(board.board,row, col, player)) {flipStonesLeftUp(row, col, player);}

        if (CheckRulesReversi.checkLegalLeftDown(board.board,row, col, player)) {flipStonesLeftDown(row, col, player);}

        if (CheckRulesReversi.checkLegalRightUp(board.board,row, col, player)) {flipStonesRightUp(row, col, player);}

        if (CheckRulesReversi.checkLegalRightDown(board.board,row, col, player)) {flipStonesRightdown(row, col, player);}
    }

    private void flipStonesUp(int row, int col, int player) {
        int opponent = CheckRulesReversi.getOpponent(player);
        for (int i = row-1; i >= 0; i--) {
            if (board.board[i][col] == opponent) {
                board.board[i][col] = player;
            }
            else {break;}
        }
    }

    private void flipStonesDown(int row, int col, int player) {
        int opponent = CheckRulesReversi.getOpponent(player);
        for (int i = row+1; i < 8; i++) {
            if (board.board[i][col] == opponent) {
                board.board[i][col] = player;
            }
            else {break;}
        }
    }

    private void flipStonesLeft(int row, int col, int player) {
        int opponent = CheckRulesReversi.getOpponent(player);
        for (int i = col-1; i >= 0; i--) {
            if (board.board[row][i] == opponent) {
                board.board[row][i] = player;
            }
            else {break;}
        }
    }

    private void flipStonesRight(int row, int col, int player) {
        int opponent = CheckRulesReversi.getOpponent(player);
        for (int i = col+1; i <= 7; i++) {
            if (board.board[row][i] == opponent) {
                board.board[row][i] = player;
            }
            else {break;}
        }
    }

    private void flipStonesLeftUp(int row, int col, int player) {
        int opponent = CheckRulesReversi.getOpponent(player);
        int j = col-1;

        for (int i = row-1; i > 0; i--) {
            if (j < 0) {
                break;
            }
            if (board.board[i][j] == opponent) {
                board.board[i][j] = player;
                j--;
            } else {
                break;
            }
        }
    }

    private void flipStonesLeftDown(int row, int col, int player) {
            int opponent = CheckRulesReversi.getOpponent(player);
            int j = col-1;

            for (int i = row+1; i <= 7; i++) {
                if (j < 0) {break;}
                if (board.board[i][j] == opponent) {
                    board.board[i][j] = player;
                    j--;
                } else {
                    break;
                }

            }
    }

    private void flipStonesRightUp(int row, int col, int player) {
        int opponent = CheckRulesReversi.getOpponent(player);
        int j = col + 1;

        for (int i = row - 1; i > 0; i--) {

            if (j > 7) {
                break;
            }
            if (board.board[i][j] == opponent) {
                board.board[i][j] = player;
                j++;
            } else {
                break;
            }
        }
    }

    private void flipStonesRightdown(int row, int col, int player) {
        int opponent = CheckRulesReversi.getOpponent(player);
        int j = col + 1;

        for (int i = row + 1; i <= 7; i++) {
            if (j > 7) {break;}
            if (board.board[i][j] == opponent) {
                board.board[i][j] = player;
                j++;
            } else {
                break;
            }
        }
    }
}
