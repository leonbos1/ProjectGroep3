package src.main.java.reversi;
import src.main.java.main.Board;
import src.main.java.tictactoe.CheckRules;

import java.util.Random;

public class ReversiAI {

    Board board;
    CheckRulesReversi rules;
    int player;

    public ReversiAI(Board board, int player) {
        this.board = board;
        this.player = player;
        rules = new CheckRulesReversi(board, player);

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

    public int[] AIMove(int player) {
        int maxRow = 0;
        int maxCol = 0;
        int maxScore= -500;
        int[][] points = new int[8][8];

        int[][] pointsBoard = new int[][] {
                {200 , -100, 100, 50 , 50 , 100, -100,  200},
                {-100, -200, -50, -50, -50, -50, -200, -100},
                {100 , -50 , 100, 0  , 0  , 100, -50 ,  100},
                {50  , -50 , 0  , 0  , 0  , 0  , -50 ,  50},
                {50  , -50 , 0  , 0  , 0  , 0  , -200, -100},
                {100 , -50 , 100, 0  , 0  , 100, -50 , -100},
                {-100, -200, -50, -50, -50, -50, -200, -100},
                {200 , -100, 100, 50 , 50 , 100, -100, 200}
        };

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (rules.checkLegalMove(row,col,player)) {
                    points[row][col] = pointsBoard[row][col];
                }
                else {points[row][col] = -1000;}
            }
        }

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (points[row][col] > maxScore) {
                    maxScore = points[row][col];
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        return new int[]{maxRow,maxCol};
    }



}