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
        int h = board.length;
        int w = board[0].length;
        if (r_start < 0 || r_start > h - 1) {
            return false;
        }
        if (c_start < 0 || c_start + (win_length - 1) > w - 1) {
            return false;
        }

        for (int i = 0; i < win_length; i++) {
            if (board[r_start][c_start + i] != player) {
                return false;
            }
        }
        return true;
    }

    public static boolean in_a_row_n_south(int player, int r_start, int c_start,int[][] board, int win_length) {
        int h = board.length;
        int w = board[0].length;

        if (r_start < 0 || r_start + (win_length-1) > h - 1) {
            return false;
        }
        if (c_start < 0 || c_start > w - 1) {
            return false;
        }

        for (int i = 0; i < win_length; i++) {
            if (board[r_start+i][c_start] != player) {
                return false;
            }
        }
        return true;
    }

    public static boolean in_a_row_n_northeast(int player, int r_start, int c_start,int[][] board, int win_length) {
        int h = board.length;
        int w = board[0].length;

        if ( r_start - (win_length-1) < 0 || r_start > h - 1) {
            return false;
        }
        if ( c_start < 0 || c_start + (win_length-1) > w - 1) {
            return false;
        }

        for (int i = 0; i < win_length; i++) {
            if (board[r_start-i][c_start+i] != player) {
                return false;
            }
        }
        return true;
    }

    public static boolean in_a_row_n_southeast(int player, int r_start, int c_start,int[][] board, int win_length) {
        int h = board.length;
        int w = board[0].length;

        if (r_start < 0 || r_start + (win_length-1) > h - 1) {
            return false;
        }
        if (c_start < 0 || c_start + (win_length-1) > w - 1) {
            return false;
        }

        for (int i = 0; i < win_length; i++) {
            if (board[r_start+i][c_start+i] != player) {
                return false;
            }
        }
        return true;
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



}