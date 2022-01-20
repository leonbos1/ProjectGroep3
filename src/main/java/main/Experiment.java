package src.main.java.main;

import src.main.java.reversi.Reversi;
import src.main.java.reversi.ReversiAI;

import java.util.Random;

public class Experiment {

    public static void main(String[] args) {

        int turn = 1;
        int depth1 = 30;
        int depth2= 2;
        double wins1 = 0;
        double wins2 = 0;
        int amountOfGames = 10;


        Reversi reversi = new Reversi(1);

        long moveTime = 0;
        long moveTime2 = 0;

        long totaltime1 = 0;
        long totaltime2 = 0;

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
                reversi.getBoard().showBoard();
            }

            while (!reversi.canPlay(2) && !reversi.gameOver()) {
                moveTime = System.currentTimeMillis();
                reversi.AIMove(1, depth1);
                moveTime = System.currentTimeMillis() - moveTime;
                totaltime1 += moveTime;
                moveCounter1++;
                reversi.getBoard().showBoard();
            }

            if (turn == 2 && reversi.canPlay(1) && !reversi.gameOver()) {
                moveTime2 = System.currentTimeMillis();
                reversi.AIMove(2, depth2);
                moveTime2 = System.currentTimeMillis() - moveTime2;
                totaltime2 += moveTime2;
                turn = 1;
                moveCounter2++;
                reversi.getBoard().showBoard();
            }

            while (!reversi.canPlay(1) && !reversi.gameOver()) {
                moveTime2 = System.currentTimeMillis();
                reversi.AIMove(2, depth2);
                moveTime2 = System.currentTimeMillis() - moveTime2;
                totaltime2 += moveTime2;
                moveCounter2++;
                reversi.getBoard().showBoard();
            }
            //System.out.printf("ai1: %s\n", moveTime);
            //System.out.printf("ai2: %s\n", moveTime2);

        }

        System.out.printf("Score ai1: %s\n", reversi.playerScore(1));
        System.out.printf("Score ai2: %s\n", reversi.playerScore(2));

        if (reversi.playerScore(1) > reversi.playerScore(2)) {
            wins1++;
        }
        if (reversi.playerScore(2) > reversi.playerScore(1)) {
            wins2++;
        }

        System.out.printf("avg duration 1: %s\n", totaltime1 / moveCounter1);
        System.out.printf("avg duration 2: %s\n", totaltime2 / moveCounter2);

        double winPercentage1 = wins1 / amountOfGames;
        double winPercentage2 = wins2 / amountOfGames;

        System.out.println(winPercentage1);
        System.out.println(winPercentage2);

    }

}
