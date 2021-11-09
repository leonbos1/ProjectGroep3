import java.util.Random;

public class TicTacToe {

    private Random randomMove;
    private Board board;
    private int player;
    private int win_length;
    private int size;

    public TicTacToe(int winLength, int size) {
        randomMove = new Random();
        board = new Board(size);
        this.win_length = winLength;
        this.player = 1;
    }

    public int[] aiMove(int ai) {
        if (canWin(ai)[0]!=-1) {
            int[] winCoordinate = canWin(ai);
            board.updateBoard(ai, winCoordinate[0], winCoordinate[1]);
            return new int[]{winCoordinate[0]+1,winCoordinate[1]+1};
        }

        else if (canWin(1)[0] != -1 ) {
            int[][] tempboard = getBoardArray();
            int[] winCoordinate = canWin(1);
            board.updateBoard(ai, winCoordinate[0], winCoordinate[1]);
            return new int[]{winCoordinate[0]+1,winCoordinate[1]+1};
        }

        else if (board.getBoard().length == 3) {
            return aiMove3x3(ai);
        }
        else {
            return aiMoveVariable(ai);
        }
    }

    public int[] aiMove3x3(int ai) {
        int row=0;
        int col=0;
        //Als ai begint: in een hoek
        if (countMoves()==1) {
            row = 0;
            col = 0;
        }

        //Als speler begint: in het midden
        else if (countMoves() == 2) {
            if (CheckRules.checkLegalMove(getBoardArray(),1,1)) {
                row = 1;
                col = 1;
            }
            else if (CheckRules.checkLegalMove(getBoardArray(),0,0)) {
                row = 0;
                col = 0;
            }
        }
        else if (countMoves() == 3) {
            if (getBoardArray()[0][0] == 0) {
                row = 0;
                col = 0;
            } else if (getBoardArray()[0][2] == 0) {
                row = 0;
                col = 2;
            } else if (getBoardArray()[2][0] == 0) {
                row = 2;
                col = 0;
            } else if (getBoardArray()[2][2] == 0) {
                row = 2;
                col = 2;
            }

            if (getBoardArray()[0][1] == 1 && getBoardArray()[1][0] == 1 && CheckRules.checkLegalMove(getBoardArray(),0,0)) {
                row = 0;
                col = 0;
            } else if (getBoardArray()[0][1] == 1 && getBoardArray()[1][2] == 1 && CheckRules.checkLegalMove(getBoardArray(),0,2)) {
                row = 0;
                col = 2;
            } else if (getBoardArray()[1][0] == 1 && getBoardArray()[2][1] == 1 && CheckRules.checkLegalMove(getBoardArray(),2,0)) {
                row = 2;
                col = 0;
            } else if (getBoardArray()[2][1] == 1 && getBoardArray()[1][2] == 1 && CheckRules.checkLegalMove(getBoardArray(),2,2)) {
                row = 2;
                col = 2;
            }
            else if (getBoardArray()[0][0] == 1 && getBoardArray()[2][2] == 1 && CheckRules.checkLegalMove(getBoardArray(),0,1)) {
                row = 0;
                col = 1;
            }
            else if (getBoardArray()[0][2] == 1 && getBoardArray()[2][0] == 1 && CheckRules.checkLegalMove(getBoardArray(),1,0)){
                row = 1;
                col = 0;
            }
        }
        else if (countMoves() == 4) {
            if (getBoardArray()[0][0] == 1 && getBoardArray()[2][2] == 1 && CheckRules.checkLegalMove(getBoardArray(),0,1)) {
                row = 0;
                col = 1;
            }
            else if (getBoardArray()[0][2] == 1 && getBoardArray()[2][0] == 1 && CheckRules.checkLegalMove(getBoardArray(),1,0)){
                row = 1;
                col = 0;
            }
        }

        else if (countMoves() == 5) {
            if (getBoardArray()[0][0] == 0) {
                row = 0;
                col = 0;
            } else if (getBoardArray()[0][2] == 0) {
                row = 0;
                col = 2;
            } else if (getBoardArray()[2][0] == 0) {
                row = 2;
                col = 0;
            } else if (getBoardArray()[2][2] == 0) {
                row = 2;
                col = 2;
            }
            else if (getBoardArray()[0][0] == 1 && getBoardArray()[2][2] == 1 && CheckRules.checkLegalMove(getBoardArray(),0,1)) {
                row = 0;
                col = 1;
            }
            else if (getBoardArray()[0][2] == 1 && getBoardArray()[2][0] == 1 && CheckRules.checkLegalMove(getBoardArray(),1,0)){
                row = 1;
                col = 0;
            }
        }

        else {
            int x = randomMove.nextInt(board.getSize());
            int y = randomMove.nextInt(board.getSize());
            if (CheckRules.checkLegalMove(getBoardArray(), x, y)) {
                row = x;
                col = y;
            } else {
                return aiMove3x3(ai);
            }
        }
        board.updateBoard(ai, row, col);
        return new int[]{row+1,col+1};
    }

    public int[] aiMoveVariable(int ai) {
        int[][] scores = scores_for_each_move(getBoardArray(),ai,1);
        int max_score = getMaxScore(scores);
        int maxRow = 0;
        int maxCol = 0;


        for (int row = 0; row < getBoardArray().length; row++) {
            for (int col = 0; col < getBoardArray().length; col++) {
                if (scores[row][col] == max_score && getBoardArray()[row][col] == 0) {
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        if (CheckRules.checkLegalMove(getBoardArray(),maxRow,maxCol)) {
            board.updateBoard(ai, maxRow, maxCol);
            return new int[]{maxRow+1,maxCol+1};
        }

        else {
            int x = randomMove.nextInt(board.getSize());
            int y = randomMove.nextInt(board.getSize());
            if (CheckRules.checkLegalMove(getBoardArray(), x, y)) {
                board.updateBoard(ai, x, y);
                return new int[]{x+1,y+1};
            } else {
                return aiMoveVariable(ai);
            }
        }

    }

    public int[] canWin(int player) {
        int maxRow = -1;
        int maxCol = -1;
        for (int row = 0; row < getBoardArray().length; row++) {
            for (int col = 0; col < getBoardArray()[0].length; col++) {
                int[][] boardCopy = getBoardArray();
                int prevMove = boardCopy[row][col];

                if (CheckRules.checkLegalMove(boardCopy,row,col)) {
                    boardCopy[row][col] = player;

                    if (CheckRules.checkWinner(boardCopy, player, getBoardWinLength())) {
                        maxRow = row;
                        maxCol = col;
                    }
                }
                boardCopy[row][col] = prevMove;
            }
        }
        return new int[]{maxRow,maxCol};


    }

    public int countMoves() {
        int moves = 1;
        int[][] board = getBoardArray();
        for (int row = 0; row < getBoardArray().length; row++) {
            for (int col = 0; col < getBoardArray()[0].length; col++) {
                if (board[row][col] != 0) {
                    moves++;
                }
            }
        }
        return moves;
    }

    public int getOpponent(int player) {
        if (player==1) {
            return 2;
        }
        return 1;
    }

    public int[][] scores_for_each_move(int[][] board, int player, int strength) {
        int[][] scores = new int[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                scores[row][col] = 50;
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {

                if (!CheckRules.checkLegalMove(board, col, row)) {
                    scores[row][col] = -1;
                }
                else if (CheckRules.checkWinner(board,player,getBoardWinLength())) {
                    scores[row][col] = 100;
                }
                else if (CheckRules.checkWinner(board,getOpponent(player),getBoardWinLength())) {
                    scores[row][col] = 0;
                }

                else if (strength == 0) {
                    if (CheckRules.checkWinner(board,player,getBoardWinLength())) {
                        scores[row][col] = 100;
                    }
                    else if (CheckRules.checkWinner(board,player,getBoardWinLength())) {
                        scores[row][col] = 0;
                    }
                    else {scores[row][col] = 50;}
                }
                else {
                    int prev_move = board[row][col];
                    board[row][col] = player;
                    if (CheckRules.checkWinner(board, player, getBoardWinLength())) {
                        scores[row][col] = 100;
                    } else if (CheckRules.checkWinner(board, getOpponent(player), getBoardWinLength())) {
                        scores[row][col] = 0;
                    }

                    int[][] opponent_scores = scores_for_each_move(board, getOpponent(player), strength - 1);

                    int opponent_max_score = getMaxScore(opponent_scores);
                    scores[row][col] = opponent_max_score;
                    board[row][col] = prev_move;
                }
            }
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
            }
        }

        return scores;
    }

    private int getMaxScore(int[][] scores) {
        int max_score = scores[0][0];
        for (int row = 0; row < board.board.length; row++) {
            for (int col = 0; col < board.board[0].length; col++) {
                if (scores[row][col] > max_score) {
                    max_score = scores[row][col];
                }
            }
        }
        return max_score;
    }

    public boolean makeMove(int row, int col) {
        if (CheckRules.checkLegalMove(getBoardArray(), row, col)) {
            board.updateBoard(getPlayer(), row, col);
            return true;
        } else {
            System.out.println("illegal move");
            return false;
        }
    }

    public void removeMove(int row,int col) {
        board.updateBoard(0,row,col);
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return this.player;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public int getBoardWinLength() {return this.win_length;}

    public int[][] getBoardArray() {
        return board.getBoard();
    }
}