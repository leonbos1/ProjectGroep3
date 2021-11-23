package src.main.java.reversi;
import src.main.java.tictactoe.CheckRules;

import java.util.Random;

public class ReversiAI {

    public static int[] AIMove(int[][] board) {
        Random random = new Random();
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
                if (CheckRulesReversi.checkLegalMove(board,row,col,2)) {
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
