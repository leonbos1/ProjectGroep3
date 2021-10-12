public class CheckRules {

    public static boolean checkLegalMove(int[][] board, int coordinate) {
        int maxcoord = board[0].length*board.length;

        if (coordinate > maxcoord || coordinate < 1) {
            return false;
        }

        int row = coordinate/board[0].length;
        int col = coordinate%board[0].length-1;

        if (board[row][col]==0) {
            return true;
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

// testen van de methodes

    public static void main(String[] args) {
        int[][] board = {{1,-1,-1},
                         {1,0,0},
                         {1,1,1}};

        int coordinate = 1;

        //System.out.println(checkLegalMove(board,coordinate));
        System.out.println(checkWinner(board,1));

    }

}