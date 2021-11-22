public class CheckRulesReversi {

    Board board;
    int player;

    public CheckRulesReversi(Board board, int player) {
        this.board = board;
        this.player = player;
    }

    public boolean checkLegalMove(int row, int col, int player) {
        if (!checkValidMove(row,col,player)) {return false;}

        else if (board.board[row][col] != 0) {return false;}

        else if (checkLegalUp(row, col, player)) {return true;}

        else if (checkLegalDown(row, col, player)) {return true;}

        else if (checkLegalLeft(row,col, player)) {return true;}

        else if (checkLegalRight(row, col, player)) {return true;}

        else if (checkLegalLeftUp(row, col, player)) {return true;}

        else if (checkLegalLeftDown(row, col, player)) {return true;}

        else if (checkLegalRightUp(row, col, player)) {return true;}

        else if (checkLegalRightDown(row, col, player)) {return true;}

        else {return false;}
    }

    public boolean checkLegalUp(int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;

        for (int i = row-1; i >= 0; i--) {

            if (board.board[i][col] == opponent) {
                opponentInCol = true;
            }
            else if (board.board[i][col] == player && opponentInCol) {
                return true;
            }
            else if  (board.board[i][col] == player && !opponentInCol) {
                return false;
            }
            else if  (board.board[i][col] == 0) {
                return false;
            }
        }
        return false;
    }

    public boolean checkLegalDown(int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;

        for (int i = row+1; i < 8; i++) {
            if (board.board[i][col] == opponent) {
                opponentInCol = true;
            }
            else if (board.board[i][col] == player && opponentInCol) {
                return true;
            }
            else if  (board.board[i][col] == player && !opponentInCol) {
                return false;
            }
            else if  (board.board[i][col] == 0) {
                return false;
            }
        }
        return false;
    }

    public boolean checkLegalLeft(int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;

        for (int i = col-1; i >= 0; i--) {
            if (board.board[row][i] == opponent) {
                opponentInCol = true;
            }
            else if (board.board[row][i] == player && opponentInCol) {
                return true;
            }
            else if  (board.board[row][i] == player && !opponentInCol) {
                return false;
            }
            else if  (board.board[row][i] == 0) {
                return false;
            }
        }
        return false;
    }

    public boolean checkLegalRight(int row, int col, int player) {
            int opponent = getOpponent(player);
            boolean opponentInCol = false;

            for (int i = col+1; i < 7; i++) {
                if (board.board[row][i] == opponent) {
                    opponentInCol = true;
                }
                else if (board.board[row][i] == player && opponentInCol) {
                    return true;
                }
                else if  (board.board[row][i] == player && !opponentInCol) {
                    return false;
                }
                else if  (board.board[row][i] == 0) {
                    return false;
                }
            }
            return false;
    }

    public boolean checkLegalLeftUp(int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col-1;

        for (int i = row-1; i > 0; i--) {
            if (j < 0) {return false;}

                if (board.board[i][j] == opponent) {
                    opponentInCol = true;
                }
                else if (board.board[i][j] == player && opponentInCol) {
                    return true;
                }
                else if  (board.board[i][j] == player && !opponentInCol) {
                    return false;
                }
                else if  (board.board[i][j] == 0) {
                    return false;
                }
                j--;
        }
        return false;
    }

    public boolean checkLegalLeftDown(int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col-1;

        for (int i = row+1; i < 7; i++) {
            if (j < 0) {return false;}

                if (board.board[i][j] == opponent) {
                    opponentInCol = true;
                }
                else if (board.board[i][j] == player && opponentInCol) {
                    return true;
                }
                else if  (board.board[i][j] == player && !opponentInCol) {
                    return false;
                }
                else if  (board.board[i][j] == 0) {
                    return false;
                }
                j--;
        }
        return false;
    }

    public boolean checkLegalRightUp(int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col + 1;

        for (int i = row-1; i > 0; i--) {

            if (j > 7) {return false;}

            if (board.board[i][j] == opponent) {
                opponentInCol = true;
            }
            else if (board.board[i][j] == player && opponentInCol) {
                return true;
            }
            else if  (board.board[i][j] == player && !opponentInCol) {
                return false;
            }
            else if  (board.board[i][j] == 0) {
                return false;
            }
            j++;
        }
        return false;
    }

    public boolean checkLegalRightDown(int row, int col, int player) {
        int opponent = getOpponent(player);
        boolean opponentInCol = false;
        int j = col + 1;

        for (int i = row+1; i < 7; i++) {

            if (j > 7) {return false;}

            if (board.board[i][j] == opponent) {
                opponentInCol = true;
            }
            else if (board.board[i][j] == player && opponentInCol) {
                return true;
            }
            else if  (board.board[i][j] == player && !opponentInCol) {
                return false;
            }
            else if  (board.board[i][j] == 0) {
                return false;
            }
            j++;
        }
        return false;
    }

        private boolean checkValidMove ( int row, int col, int player){

            if (row < 0 || col < 0) {
                return false;
            } else if (row > 7 || col > 7) {
                return false;
            } else if (checkValidRowEast(row, col, player)) {
                return true;
            } else if (checkValidRowSouth(row, col, player)) {
                return true;
            } else if (checkValidRowNorthEast(row, col, player)) {
                return true;
            } else if (checkValidRowSouthEast(row, col, player)) {
                return true;
            } else {
                return false;
            }

        }

        private boolean checkValidRowEast ( int row, int col, int player){

            for (int i = 0; i < 8; i++) {
                if (board.board[row][i] == player) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkValidRowSouth ( int row, int col, int player){

            for (int i = 0; i < 8; i++) {
                if (board.board[i][col] == player) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkValidRowNorthEast ( int row, int col, int player){
            col = (row + col) - 7;
            row = 7;

            if (col < 0) {
                col *= -1;
            }

            for (int i = 0; i < 8; i++) {
                if (row - i < 0 || col + i > 7) {
                    return false;
                }
                if (board.board[row - i][col + i] == player) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkValidRowSouthEast ( int row, int col, int player){
            row = row - col;
            col = 0;
            if (row < 0) {
                row *= -1;
            }

            for (int i = 0; i < 8; i++) {
                if (row + i > 7 || col + i > 7) {
                    return false;
                }
                if (board.board[row + i][col + i] == player) {
                    return true;
                }
            }

            return false;
        }

        public int getOpponent ( int player){
            if (player == 1) {
                return 2;
            } else {
                return 1;
            }
        }
}
