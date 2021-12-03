package src.main.java.reversi;
import src.main.java.main.Board;
import src.main.java.tictactoe.CheckRules;

import java.util.Random;



public class ReversiAI {

    private static final int[][] pointsBoard = new int[][] {
        {200 , -100, 100, 50 , 50 , 100, -100,  200},
        {-100, -200, -50, -50, -50, -50, -200, -100},
        {100 , -50 , 100, 0  , 0  , 100, -50 ,  100},
        {50  , -50 , 0  , 0  , 0  , 0  , -50 ,  50},
        {50  , -50 , 0  , 0  , 0  , 0  , -200, -100},
        {100 , -50 , 100, 0  , 0  , 100, -50 , -100},
        {-100, -200, -50, -50, -50, -50, -200, -100},
        {200 , -100, 100, 50 , 50 , 100, -100, 200}
    };

    Board board;
    CheckRulesReversi rules;
    int player;

    public ReversiAI(Board board, int player) {
        this.board = board;
        this.player = player;

    }

    public int[] RandomAIMove() {

        Random random = new Random();
        int x = random.nextInt(8);
        int y = random.nextInt(8);
        int row = 0;
        int col = 0;
        if (rules.checkLegalMove(x, y, player)) {
            row = x;
            col = y;
        } else {
            return RandomAIMove();
        }
        return new int[]{row, col};
    }

    public int[] AIMove(Reversi reversi, int player, int depth) {
        Reversi reversiCopy = new Reversi(player);

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                reversiCopy.board.board[row][col] = reversi.getBoardArray()[row][col];
            }
        }

        int[][] points = pointsForEachMove(reversiCopy, player, depth);

        int maxscore = -10000;
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (maxscore < points[row][col]) {
                    maxscore = points[row][col];
                    maxRow = row;
                    maxCol = col;
                }
            }
        }
        System.out.println("een zet");
        return new int[]{maxRow,maxCol};

    }

    public int [][] pointsForEachMove(Reversi reversi, int player, int depth) {
        int[][] points = new int[8][8];
        int maxScore = -10000;

        rules = new CheckRulesReversi(reversi.board, player);

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (!rules.checkLegalMove(row,col, player)) {
                    points[row][col] = -1000;
                }

                else if (rules.gameOver() && (rules.checkPlayerScore(player) > rules.checkPlayerScore(Reversi.getOpponent(player))) ) {
                    points[row][col] = 1000;
                }

                else if (rules.gameOver() && (rules.checkPlayerScore(player) < rules.checkPlayerScore(Reversi.getOpponent(player))) ) {
                    points[row][col] = -500;
                }

                else if (depth == 0) {
                    points[row][col] = pointsBoard[row][col];
                    for (int i = 0; i <= 7; i++) {
                        for (int j = 0; j <= 7; j++) {
                            System.out.println(points[i][j]);
                        }
                    }

                }

                else {
                    if (rules.checkLegalMove(row,col,player)) {
                        reversi.makeMove(player, row, col);


                        for (int i = 0; i <= 7; i++) {
                            for (int j = 0; j <= 7; j++) {
                                if (rules.checkLegalMove(Reversi.getOpponent(player), i, j)) {
                                    points[i][j] = pointsBoard[i][j];
                                    if (maxScore < points[i][j]) {
                                        maxScore = points[i][j];
                                        row = i;
                                        col = j;
                                    }
                                }
                            }
                        }
                        reversi.makeMove(Reversi.getOpponent(player),row,col);


                        pointsForEachMove(reversi, player, depth-1);
                    }

                }

            }
        }

        return points;
    }

}