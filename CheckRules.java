public class CheckRules {

    public static boolean checkLegalMove(int[][] board, int row, int col) {

        if (row >= board.length || col >= board[0].length) {
            return false;
        } else if (board[row][col] == 0) {
            return true;
        }
        return false;
    }

    public static boolean checkWinner(int[][] board, int player) {
        int highDiagonalCounter = 0;
        for (int row = 0; row < board.length; row++) {

            if (board[row][row] == player) {
                highDiagonalCounter++;
                if (highDiagonalCounter==board.length) {
                    return true;
                }
            }

            int horizontalCounter = 0;
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == player) {
                    horizontalCounter++;
                    if (horizontalCounter == board[0].length) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }

       for (int col = 0; col < board.length; col++) {

            int verticalCounter = 0;
            for (int row = 0; row < board[0].length; row++) {
                if (board[row][col] == player) {
                    verticalCounter++;
                    if (verticalCounter == board[0].length) {
                        return true;
                    }
                } else {
                    break;
                }
            }
       }

       int lowDiagonalCounter = 0;
       int j = 0;
       for (int i = board[0].length-1; i >= 0; i-- ) {
               if (board[i][j] == player) {
                   lowDiagonalCounter++;
                    if (lowDiagonalCounter==board.length) {
                        return true;
                    }
               }
               j++;

       }

       return false;

            }

// testen van de methodes

    public static void main(String[] args) {
        int[][] board = {{1,0,0,1,1},
                         {1,1,0,1,0},
                         {0,0,0,0,0},
                         {0,1,0,1,1},
                         {0,1,1,0,1}};

        int coordinate = 1;

        //System.out.println(checkLegalMove(board,2,1));
        //System.out.println(checkWinner(board,1));

    }

}