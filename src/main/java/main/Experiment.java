package src.main.java.main;

import src.main.java.reversi.Reversi;
import src.main.java.reversi.ReversiAI;

import java.io.IOException;
import java.util.Random;

public class Experiment {

    public static void main(String[] args) throws Exception {


        int turn = 1;
        int depth1 = 5;
        double wins1 = 0;
        double wins2 = 0;

        Reversi reversi = new Reversi(1);

        long moveTime = 0;
        long totaltime1 = 0;
        int moveCounter1 = 0;
        int moveCounter2 = 0;


        while (!reversi.gameOver()) {

            if (turn == 1 && reversi.canPlay(2) && !reversi.gameOver()) {
                moveTime = System.currentTimeMillis();
                reversi.AIMove(1, depth1);
                moveTime = System.currentTimeMillis() - moveTime;
                totaltime1 += moveTime;
                turn = 2;
                moveCounter1++;
                //reversi.getBoard().showBoard();
            }

            while (!reversi.canPlay(2) && !reversi.gameOver()) {
                moveTime = System.currentTimeMillis();
                reversi.AIMove(1, depth1);
                moveTime = System.currentTimeMillis() - moveTime;
                totaltime1 += moveTime;
                moveCounter1++;
                //reversi.getBoard().showBoard();
            }

            if (turn == 2 && reversi.canPlay(1) && !reversi.gameOver()) {
                reversi.AIPointMove(reversi,2);
                turn = 1;
                moveCounter2++;
                //reversi.getBoard().showBoard();
            }

            while (!reversi.canPlay(1) && !reversi.gameOver()) {
                reversi.AIPointMove(reversi,2);
                moveCounter2++;
                //reversi.getBoard().showBoard();
            }
            //System.out.printf("ai1: %s\n", moveTime);
            //System.out.printf("ai2: %s\n", moveTime2);

        }

        System.out.printf("Score ai1: %s\n", reversi.playerScore(1));
        System.out.printf("Score ai2: %s\n", reversi.playerScore(2));

        if (reversi.playerScore(1) > reversi.playerScore(2)) {
            System.out.println("minimax wint");
        }
        else {
            System.out.println("minimax verliest");
        }


        System.out.printf("avg duration 1: %s\n", totaltime1 / moveCounter1);


    }



}
