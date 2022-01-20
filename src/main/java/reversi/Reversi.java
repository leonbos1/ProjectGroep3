package src.main.java.reversi;

import src.main.java.main.Board;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Reversi {
    public Board board;
    private int player;
    ReversiAI ai;
    CheckRulesReversi rules;

    public Reversi(int player) {
        this.board = new Board(8,8);
        board.updateBoard(1, 3, 3);
        board.updateBoard(1, 4, 4);
        board.updateBoard(2, 3, 4);
        board.updateBoard(2, 4, 3);
        this.player = player;
        ai = new ReversiAI(board, getOpponent(player));
        rules = new CheckRulesReversi(board, player);

    }


    public void makeMove(int player, int row, int col) {
        board.updateBoard(player, row, col);
        int opponent = getOpponent(player);

        if (rules.checkLegalUp(row, col, player)) {
            for (int i = row-1; i >= 0; i--) {
                if (board.board[i][col] == opponent) {
                    board.board[i][col] = player;
                }
                else {break;}
            }
        }

        if (rules.checkLegalDown(row, col, player)) {
            for (int i = row+1; i < 8; i++) {
                if (board.board[i][col] == opponent) {
                    board.board[i][col] = player;
                }
                else {break;}
            }
        }

        if (rules.checkLegalLeft(row,col, player)) {
            for (int i = col-1; i >= 0; i--) {
                if (board.board[row][i] == opponent) {
                    board.board[row][i] = player;
                }
                else {break;}
            }
        }

        if (rules.checkLegalRight(row, col, player)) {
            for (int i = col+1; i <= 7; i++) {
                if (board.board[row][i] == opponent) {
                    board.board[row][i] = player;
                }
                else {break;}
            }
        }

        if (rules.checkLegalLeftUp(row, col, player)) {
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

        if (rules.checkLegalLeftDown(row, col, player)) {
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

        if (rules.checkLegalRightUp(row, col, player)) {
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

        if (rules.checkLegalRightDown(row, col, player)) {
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

    public boolean playerMove(int row, int col) {
        if (rules.checkLegalMove(row, col, getPlayer())) {
            makeMove(player, row, col);
            return true;
        } else {
            System.out.println("illegal move");
            return false;
        }
    }

    public int[] AIMoveTry(int player, int depth) {

        int[] result = ai.AIMove(this,player, depth);

        if (!rules.checkLegalMove(result[0],result[1],player)) {
            System.out.println("Illegaal!!!!!!!!!!!!!!!!");
        }

        makeMove(player, result[0],result[1]);

        result[0] +=1;
        result[1] +=1;

        return result;
    }

    public int[] AIMove(int player, int depth) {
        int[] result = ai.AIMove(this, player, depth);

        makeMove(player, result[0], result[1]);
        result[0]++;
        result[1]++;
        return result;
    }

    public ArrayList<int[]> possibleMoves(int player) {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (rules.checkLegalMove(row, col, player)) {
                    int[] move = {row, col};
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    public boolean canPlay(int player) {
        CheckRulesReversi rules = new CheckRulesReversi(getBoard(), player);
        return rules.canPlay(player);
    }

    public boolean gameOver() {
        CheckRulesReversi rules = new CheckRulesReversi(getBoard(), player);
        return rules.gameOver();
    }

    public int playerScore(int player) {
        CheckRulesReversi rules = new CheckRulesReversi(getBoard(), player);
        return rules.checkPlayerScore(player);
    }

    public Board getBoard() {
        return board;
    }

    public int getPlayer() {
        return player;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setBoardArray(int[][]board) {this.board.board = board;}

    public void setPlayer(int player) {
        this.player = player;
    }

    public int[][] getBoardArray() {
        return board.getBoard();
    }

    public static int getOpponent(int player) {
        if (player == 1) {
            return 2;
        } else {
            return 1;
        }
    }
}

