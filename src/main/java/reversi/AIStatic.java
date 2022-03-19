package src.main.java.reversi;

import src.main.java.main.Board;

public class AIStatic {


    public static boolean checkLegalMove(int[][] board, int row, int col, int player) {
        if (!checkValidMove(board, row,col,player)) {return false;}

        else if (board[row][col] != 0) {return false;}

        else if (checkLegalUp(board, row, col, player)) {return true;}

        else if (checkLegalDown(board, row, col, player)) {return true;}

        else if (checkLegalLeft(board, row,col, player)) {return true;}

        else if (checkLegalRight(board, row, col, player)) {return true;}

        else if (checkLegalLeftUp(board, row, col, player)) {return true;}

        else if (checkLegalLeftDown(board, row, col, player)) {return true;}

        else if (checkLegalRightUp(board, row, col, player)) {return true;}

        else if (checkLegalRightDown(board, row, col, player)) {return true;}

        else {return false;}
    }

    public static boolean checkLegalUp(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;

        for (int i = row-1; i >= 0; i--) {

            if (board[i][col] == opponent) {
                opponentInCol = true;
            }
            else if (board[i][col] == player && opponentInCol) {
                return true;
            }
            else if  (board[i][col] == player && !opponentInCol) {
                return false;
            }
            else if  (board[i][col] == 0) {
                return false;
            }
        }
        return false;
    }

    public static boolean checkLegalDown(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;

        for (int i = row+1; i < 8; i++) {
            if (board[i][col] == opponent) {
                opponentInCol = true;
            }
            else if (board[i][col] == player && opponentInCol) {
                return true;
            }
            else if  (board[i][col] == player && !opponentInCol) {
                return false;
            }
            else if  (board[i][col] == 0) {
                return false;
            }
        }
        return false;
    }

    public static boolean checkLegalLeft(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;

        for (int i = col-1; i >= 0; i--) {
            if (board[row][i] == opponent) {
                opponentInCol = true;
            }
            else if (board[row][i] == player && opponentInCol) {
                return true;
            }
            else if  (board[row][i] == player && !opponentInCol) {
                return false;
            }
            else if  (board[row][i] == 0) {
                return false;
            }
        }
        return false;
    }

    public static boolean checkLegalRight(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;

        for (int i = col+1; i < 8; i++) {
            if (board[row][i] == opponent) {
                opponentInCol = true;
            }
            else if (board[row][i] == player && opponentInCol) {
                return true;
            }
            else if  (board[row][i] == player && !opponentInCol) {
                return false;
            }
            else if  (board[row][i] == 0) {
                return false;
            }
        }
        return false;
    }

    public static boolean checkLegalLeftUp(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col-1;

        for (int i = row-1; i >= 0; i--) {
            if (j < 0) {return false;}

            if (board[i][j] == opponent) {
                opponentInCol = true;
            }
            else if (board[i][j] == player && opponentInCol) {
                return true;
            }
            else if  (board[i][j] == player && !opponentInCol) {
                return false;
            }
            else if  (board[i][j] == 0) {
                return false;
            }
            j--;
        }
        return false;
    }

    public static boolean checkLegalLeftDown(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col-1;

        for (int i = row+1; i < 8; i++) {
            if (j < 0) {return false;}

            if (board[i][j] == opponent) {
                opponentInCol = true;
            }
            else if (board[i][j] == player && opponentInCol) {
                return true;
            }
            else if  (board[i][j] == player && !opponentInCol) {
                return false;
            }
            else if  (board[i][j] == 0) {
                return false;
            }
            j--;
        }
        return false;
    }

    public static boolean checkLegalRightUp(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col + 1;

        for (int i = row-1; i >= 0; i--) {

            if (j > 7) {return false;}

            if (board[i][j] == opponent) {
                opponentInCol = true;
            }
            else if (board[i][j] == player && opponentInCol) {
                return true;
            }
            else if  (board[i][j] == player && !opponentInCol) {
                return false;
            }
            else if  (board[i][j] == 0) {
                return false;
            }
            j++;
        }
        return false;
    }

    public static boolean checkLegalRightDown(int[][] board, int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col + 1;

        for (int i = row+1; i < 8; i++) {

            if (j > 7) {return false;}

            if (board[i][j] == opponent) {
                opponentInCol = true;
            }
            else if (board[i][j] == player && opponentInCol) {
                return true;
            }
            else if  (board[i][j] == player && !opponentInCol) {
                return false;
            }
            else if  (board[i][j] == 0) {
                return false;
            }
            j++;
        }
        return false;
    }


    private static boolean checkValidMove (int[][] board, int row, int col, int player){

        if (row < 0 || col < 0) {
            return false;
        } else if (row > 7 || col > 7) {
            return false;
        } else if (checkValidRowEast(board, row, col, player)) {
            return true;
        } else if (checkValidRowSouth(board, row, col, player)) {
            return true;
        } else if (checkValidRowNorthEast(board, row, col, player)) {
            return true;
        } else if (checkValidRowSouthEast(board, row, col, player)) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean checkValidRowEast (int[][] board,  int row, int col, int player){

        for (int i = 0; i < 8; i++) {
            if (board[row][i] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkValidRowSouth (int[][] board,  int row, int col, int player){

        for (int i = 0; i < 8; i++) {
            if (board[i][col] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkValidRowNorthEast (int[][] board,  int row, int col, int player){
        col = (row + col) - 7;
        row = 7;

        if (col < 0) {
            col *= -1;
        }

        for (int i = 0; i < 8; i++) {
            if (row - i < 0 || col + i > 7) {
                return false;
            }
            if (board[row - i][col + i] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkValidRowSouthEast (int[][] board,  int row, int col, int player){
        row = row - col;
        col = 0;
        if (row < 0) {
            row *= -1;
        }

        for (int i = 0; i < 8; i++) {
            if (row + i > 7 || col + i > 7) {
                return false;
            }
            if (board[row + i][col + i] == player) {
                return true;
            }
        }

        return false;
    }

    public static boolean canPlay(int[][] board, int player) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (checkLegalMove(board, row, col, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean gameOver(int[][] board) {
        if (!canPlay(board, 1) && !canPlay(board, 2)) {
            return true;
        }
        return false;
    }

    public static int checkPlayerScore(int[][] board, int player) {
        int playerScore = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == player) {
                    playerScore++;
                }
            }
        }
        return playerScore;
    }

    public static int getOpponent (int player){
        if (player == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    public static int[][] makeMove(int[][] board, int player, int row, int col) {
        board[row][col] = player;
        int opponent = getOpponent(player);

        if (checkLegalUp(board, row, col, player)) {
            for (int i = row-1; i >= 0; i--) {
                if (board[i][col] == opponent) {
                    board[i][col] = player;
                }
                else {break;}
            }
        }

        if (checkLegalDown(board, row, col, player)) {
            for (int i = row+1; i < 8; i++) {
                if (board[i][col] == opponent) {
                    board[i][col] = player;
                }
                else {break;}
            }
        }

        if (checkLegalLeft(board, row,col, player)) {
            for (int i = col-1; i >= 0; i--) {
                if (board[row][i] == opponent) {
                    board[row][i] = player;
                }
                else {break;}
            }
        }

        if (checkLegalRight(board, row, col, player)) {
            for (int i = col+1; i <= 7; i++) {
                if (board[row][i] == opponent) {
                    board[row][i] = player;
                }
                else {break;}
            }
        }

        if (checkLegalLeftUp(board, row, col, player)) {
            int j = col-1;

            for (int i = row-1; i > 0; i--) {
                if (j < 0) {
                    break;
                }
                if (board[i][j] == opponent) {
                    board[i][j] = player;
                    j--;
                } else {
                    break;
                }
            }
        }

        if (checkLegalLeftDown(board, row, col, player)) {
            int j = col-1;

            for (int i = row+1; i <= 7; i++) {
                if (j < 0) {break;}
                if (board[i][j] == opponent) {
                    board[i][j] = player;
                    j--;
                } else {
                    break;
                }

            }
        }

        if (checkLegalRightUp(board, row, col, player)) {
            int j = col + 1;

            for (int i = row - 1; i > 0; i--) {

                if (j > 7) {
                    break;
                }
                if (board[i][j] == opponent) {
                    board[i][j] = player;
                    j++;
                } else {
                    break;
                }
            }
        }

        if (checkLegalRightDown(board, row, col, player)) {
            int j = col + 1;

            for (int i = row + 1; i <= 7; i++) {
                if (j > 7) {break;}
                if (board[i][j] == opponent) {
                    board[i][j] = player;
                    j++;
                } else {
                    break;
                }
            }
        }
        return board;
    }

}
