package src.main.java.reversi;

import src.main.java.main.Board;

import java.util.Random;

public class Reversi {
    private Random randomMove;
    private Board board;
    private int player;

    public Reversi(int player) {
        this.randomMove = new Random();
        this.board = new Board(8);
        board.updateBoard(1, 3, 3);
        board.updateBoard(1, 4, 4);
        board.updateBoard(2, 3, 4);
        board.updateBoard(2, 4, 3);
        this.player = player;
    }

    private void makeMove(int player, int row, int col) {
        CheckRulesReversi rules = new CheckRulesReversi(getBoard(), getPlayer());
        board.updateBoard(player, row, col);

        if (rules.checkLegalUp(row, col, player)) {
            for (int i = row-1; i >= 0; i--) {
                if (board.getBoard()[i][col] == player) {
                    break;
                } else {
                    board.updateBoard(player, i, col);
                }
            }
        }

        if (rules.checkLegalDown(row, col, player)) {
            for (int i = row+1; i < 8; i++) {
                if (board.board[i][col] == player) {
                    break;
                } else {
                    board.updateBoard(player, i, col);
                }
            }
        }

        if (rules.checkLegalLeft(row,col, player)) {
            for (int i = col-1; i >= 0; i--) {
                if (board.board[row][i] == player) {
                    break;
                } else {
                    board.updateBoard(player, row, i);
                }
            }
        }

        if (rules.checkLegalRight(row, col, player)) {
            for (int i = col+1; i < 8; i++) {
                if (board.board[row][i] == player) {
                    break;
                } else {
                    board.updateBoard(player, row, i);
                }
            }
        }

        if (rules.checkLegalLeftUp(row, col, player)) {
            int j = col-1;
            for (int i = row-1; i > 0; i--) {
                if (j < 0) {
                    break;
                }
                if (board.board[i][j] == player) {
                    break;
                } else {
                    board.updateBoard(player, i, j);
                }
                j--;
            }
        }

        if (rules.checkLegalLeftDown(row, col, player)) {
            int j = col-1;
            for (int i = row+1; i < 7; i++) {
                if (j < 0) {
                    break;
                }
                if (board.board[i][j] == player) {
                    break;
                } else {
                    board.updateBoard(player, i, j);
                }
                j--;
            }
        }

        if (rules.checkLegalRightUp(row, col, player)) {
            int j = col + 1;
            for (int i = row-1; i > 0; i--) {
                if (j > 7) {
                    break;
                }
                if (board.board[i][j] == player) {
                    break;
                } else {
                    board.updateBoard(player, i, j);
                }
                j++;
            }
        }

        if (rules.checkLegalRightDown(row, col, player)) {
            int j = col + 1;
            for (int i = row+1; i < 7; i++) {
                if (j > 7) {
                    break;
                }
                if (board.board[i][j] == player) {
                    break;
                } else {
                    board.updateBoard(player, i, j);
                }
                j++;
            }
        }
    }

    public boolean playerMove(int row, int col) {
        CheckRulesReversi rules = new CheckRulesReversi(getBoard(), getPlayer());
        if (rules.checkLegalMove(row, col, getPlayer())) {
            makeMove(player, row, col);
            return true;
        } else {
            System.out.println("illegal move");
            return false;
        }
    }

    public int[] aiMove() {
        CheckRulesReversi rules = new CheckRulesReversi(getBoard(), getOpponent());
        int[] move = {randomMove.nextInt(8), randomMove.nextInt(8)};
        while (!rules.checkLegalMove(move[0], move[1], getOpponent())) {
            move[0] = randomMove.nextInt(8);
            move[1] = randomMove.nextInt(8);
        }
        makeMove(getOpponent(), move[0], move[1]);
        return move;
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
        return rules.checkPlayerScore();
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

    public void setPlayer(int player) {
        this.player = player;
    }

    public int[][] getBoardArray() {
        return board.getBoard();
    }

    public int getOpponent() {
        if (player == 1) {
            return 2;
        } else {
            return 1;
        }
    }
}

