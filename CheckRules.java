public class CheckRules {

    public static boolean checkLegalMove(int[][] board, int coordinate) {

        if (coordinate < 1 || coordinate > 9) {
            return false;
        }
        if (coordinate == 1) {
            if (board[0][0]==0) {
                return true;
            }
        }
        if (coordinate == 2) {
            if (board[0][1]==0) {
                return true;
            }
        }
        if (coordinate == 3) {
            if (board[0][2]==0) {
                return true;
            }
        }
        if (coordinate == 4) {
            if (board[1][0]==0) {
                return true;
            }
        }
        if (coordinate == 5) {
            if (board[1][1]==0) {
                return true;
            }
        }
        if (coordinate == 6) {
            if (board[1][2]==0) {
                return true;
            }
        }
        if (coordinate == 7) {
            if (board[2][0]==0) {
                return true;
            }
        }
        if (coordinate == 8) {
            if (board[2][1]==0) {
                return true;
            }
        }
        if (coordinate == 9) {
            if (board[2][2]==0) {
                return true;
            }
        }
        return false;

    }

    public static boolean checkWinner(int[][] board, int player) {

        if (board[0][0] == player && board[0][1] == player && board[0][2] == player) {
            return true;
        }
        if (board[1][0] == player && board[1][1] == player && board[1][2] == player) {
            return true;
        }
        if (board[2][0] == player && board[2][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][0] == player && board[1][0] == player && board[2][0] == player) {
            return true;
        }
        if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][2] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return false;


    }

/* testen van de methodes

    public static void main(String[] args) {
        int[][] board = {{1,0,0},
                         {0,1,0},
                         {0,1,1}};
        int coordinate = 2;

        //System.out.println(checkLegalMove(board,coordinate));
        System.out.println(checkWinner(board,1));

    }
*/
}