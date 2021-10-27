public class CheckRules {

    public static boolean checkLegalMove(int[][] board, int row, int col) {

        if (row >= board.length || col >= board[0].length) {
            return false;
        } else if (board[row][col] == 0) {
            return true;
        }
        return false;
    }

    public static boolean checkWinner(int[][] board, int player, int win_length) {
        for (int row = 0; row < board[0].length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (in_a_row_n_east(player,row, col, board,win_length)) {
                    return true;
            }
                if (in_a_row_n_south(player,row, col, board,win_length)) {
                    return true;
                }
                if (in_a_row_n_northeast(player,row, col, board,win_length)) {
                    return true;
                }
                if (in_a_row_n_southeast(player,row, col, board,win_length)) {
                    return true;
                }
        }
    }
        return false;


    }

    public static boolean in_a_row_n_east(int player, int r_start, int c_start,int[][] board, int win_length) {
        /*Starting from (row, col) of (r_start, c_start)
       within the 2d list-of-lists a (array),
       returns True if there are n ch's in a row
       heading east and returns False otherwise.
        */
        int h = board.length;
        int w = board[0].length;
        if (r_start < 0 || r_start > h - 1) {
            return false;
        }  // rij buiten de grenzen
        if (c_start < 0 || c_start + (win_length - 1) > w - 1) {
            return false;
        }// kolom buiten de grenzen
        // lus over elke _offset_ i van de locatie
        for (int i = 0; i < win_length; i++) {
            if (board[r_start][c_start + i] != player) {  //klopt niet !
                return false;
            }
        }
        return true;  // alle offsets kloppen, dus we geven True terug
    }

    public static boolean in_a_row_n_south(int player, int r_start, int c_start,int[][] board, int win_length) {
        /*Starting from (row, col) of (r_start, c_start)
       within the 2d list-of-lists a (array),
       returns True if there are n ch's in a row
       heading south and returns False otherwise.
        */
        int h = board.length;
        int w = board[0].length;

        if (r_start < 0 || r_start + (win_length-1) > h - 1) {
            return false;
        }  // rij buiten de grenzen
        if (c_start < 0 || c_start > w - 1) {
            return false;
        }// kolom buiten de grenzen

        // lus over elke _offset_ i van de locatie
        for (int i = 0; i < win_length; i++) {
            if (board[r_start+i][c_start] != player) {  //klopt niet !
                return false;
            }
        }
        return true;  // alle offsets kloppen, dus we geven True terug
    }

    public static boolean in_a_row_n_northeast(int player, int r_start, int c_start,int[][] board, int win_length) {
        /*Starting from (row, col) of (r_start, c_start)
       within the 2d list-of-lists a (array),
       returns True if there are n ch's in a row
       heading northeast and returns False otherwise.
       */
        int h = board.length;
        int w = board[0].length;

        if ( r_start - (win_length-1) < 0 || r_start > h - 1) {
            return false;
        }  // rij buiten de grenzen
        if ( c_start < 0 || c_start + (win_length-1) > w - 1) {
            return false;
        }// kolom buiten de grenzen

        // lus over elke _offset_ i van de locatie
        for (int i = 0; i < win_length; i++) {
            if (board[r_start-i][c_start+i] != player) {  //klopt niet !
                return false;
            }
        }
        return true;  // alle offsets kloppen, dus we geven True terug
    }

    public static boolean in_a_row_n_southeast(int player, int r_start, int c_start,int[][] board, int win_length) {
         /*Starting from (row, col) of (r_start, c_start)
       within the 2d list-of-lists a (array),
       returns True if there are n ch's in a row
       heading southeast and returns False otherwise.
       */
        int h = board.length;
        int w = board[0].length;

        if (r_start < 0 || r_start + (win_length-1) > h - 1) {
            return false;
        }  // rij buiten de grenzen
        if (c_start < 0 || c_start + (win_length-1) > w - 1) {
            return false;
        }// kolom buiten de grenzen

        // lus over elke _offset_ i van de locatie
        for (int i = 0; i < win_length; i++) {
            if (board[r_start+i][c_start+i] != player) {  //klopt niet !
                return false;
            }
        }
        return true;  // alle offsets kloppen, dus we geven True terug
    }

    public static boolean checkBoardFull(int[][] board) {
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


// testen van de methodes


}