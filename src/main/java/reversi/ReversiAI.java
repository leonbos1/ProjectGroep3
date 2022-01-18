package src.main.java.reversi;
import src.main.java.main.Board;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;



public class ReversiAI {

    long start;
    long end;
    int timeOutTimer;
    int maxTreeDepth = 25;

    private static final int[][] pointsBoard = new int[][]{
            {500, -100, 100, 50, 50, 100, -100, 500},
            {-100, -200, -50, -50, -50, -50, -200, -100},
            {100, -50, 100, 0, 0, 100, -50, 100},
            {50, -50, 0, 0, 0, 0, -50, 50},
            {50, -50, 0, 0, 0, 0, -50, 50},
            {100, -50, 100, 0, 0, 100, -50, 100},
            {-100, -200, -50, -50, -50, -50, -200, -100},
            {500, -100, 100, 50, 50, 100, -100, 500}
    };

    Board board;
    CheckRulesReversi rules;
    int player;
    boolean myTurn = true;

    public ReversiAI(Board board, int player) {
        this.board = board;
        this.player = player;

    }


    public static Reversi copyReversi(Reversi reversi) {
        Board newBoard = new Board(8, 8);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                newBoard.board[row][col] = reversi.getBoardArray()[row][col];
            }
        }
        Reversi tryGame = new Reversi(reversi.getPlayer());
        tryGame.setBoard(newBoard);
        tryGame.ai = reversi.ai;
        tryGame.rules = new CheckRulesReversi(tryGame.getBoard(), tryGame.getPlayer());
        return tryGame;
    }


    private static int[] bestMove(Reversi reversi, int depth, int player) {
        int bestScore = -99999;
        int[] bestMove = new int[]{0, 0};
        Reversi reversiCopy = copyReversi(reversi);
        CheckRulesReversi newRules = new CheckRulesReversi(reversiCopy.getBoard(), player);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (newRules.checkLegalMove(row, col, player)) {
                    reversiCopy.makeMove(player, row, col);
                    int score = MiniMax(reversi, depth - 1, true, newRules.player);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{row, col};
                    }
                }
            }
        }
        return bestMove;
    }

    private static int checkWinner(Reversi reversi) {
        if (reversi.playerScore(1) > reversi.playerScore(2)) {
            return 1;
        } else if (reversi.playerScore(1) < reversi.playerScore(2)) {
            return 2;
        } else {
            return 3;
        }

    }

    private static int evaluateBoard(Reversi reversi, int player) {

        return (reversi.playerScore(player) - reversi.playerScore(Reversi.getOpponent(player)));
    }

    private static boolean isTerminalNode(Reversi reversi, int player) {
        CheckRulesReversi newRules = new CheckRulesReversi(reversi.getBoard(), player);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (newRules.checkLegalMove(row, col, player)) {
                    return false;
                }
            }
        }
        return true;

    }

    private static int MiniMax(Reversi reversi, int depth, boolean myTurn, int player) {
        System.out.println("minimax!!!!");
        Reversi reversiCopy = copyReversi(reversi);

        ArrayList<int[]> possibleMoves = possibleMoves(reversiCopy, player);

        if (isTerminalNode(reversiCopy, player) || depth == 0) {
            return evaluateBoard(reversiCopy, player);
        }
        int bestScore;
        if (myTurn) {
            bestScore = -99999;
            for (int[] position : possibleMoves) {
                reversiCopy.makeMove(player, position[0], position[1]);
                int Score = MiniMax(reversiCopy, depth - 1, false, player);
                if (Score > bestScore) {
                    bestScore = Score;
                }
            }
        } else {
            bestScore = 99999;
            for (int[] position : possibleMoves) {
                reversi.makeMove(Reversi.getOpponent(player), position[0], position[1]);
                int Score = MiniMax(reversi, depth - 1, true, player);
                if (Score < bestScore) {
                    bestScore = Score;
                }

            }
        }
        return bestScore;
    }

    private static int emptySpaces(Board board) {
        int emptySpaces = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.board[row][col] == 0) {
                    emptySpaces++;
                }
            }
        }
        return emptySpaces;
    }

    private static ArrayList<int[]> possibleMoves(Reversi reversi, int player) {
        CheckRulesReversi newRules = new CheckRulesReversi(reversi.getBoard(), player);
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (newRules.checkLegalMove(row, col, player)) {
                    possibleMoves.add(new int[]{row, col});
                }
            }
        }
        return possibleMoves;
    }

    private static int[] pointsBoardMove(Reversi reversi, int player) {
        int highestScore = -1000;
        int[] highestPosition = new int[]{-1, -1};
        ArrayList<int[]> possibleMoves = possibleMoves(reversi, player);

        for (int[] position : possibleMoves) {
            if (pointsBoard[position[0]][position[1]] > highestScore) {
                highestScore = pointsBoard[position[0]][position[1]];
                highestPosition[0] = position[0];
                highestPosition[1] = position[1];
            }
        }
        return highestPosition;
    }

    public int[] AIMove(Reversi reversi, int player) {
        CheckRulesReversi newRules = new CheckRulesReversi(reversi.getBoard(),player);


        if (emptySpaces(reversi.getBoard()) <= maxTreeDepth) {
            int[] move = bestMove(reversi, maxTreeDepth, player);
            if (newRules.checkLegalMove(move[0], move[1], player)) {
                return move;
            } else {
                System.out.println("illegale minimax move");
                return pointsBoardMove(reversi, player);
            }
        }
        else {
            return pointsBoardMove(reversi, player);
        }



    }
}
