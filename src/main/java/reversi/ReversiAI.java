package src.main.java.reversi;
import src.main.java.main.Board;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;



public class ReversiAI {

    int maxTreeDepth = 20;
    public static final ArrayList<int[]> badTiles = new ArrayList<>();

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
    int player;
    public ReversiAI(Board board, int player) {
        this.board = board;
        this.player = player;
        badTiles.add(new int[] {0,1});  badTiles.add(new int[] {0,6});  badTiles.add(new int[] {1,1});  badTiles.add(new int[] {1,0});
        badTiles.add(new int[] {1,6}); badTiles.add(new int[] {1,7}); badTiles.add(new int[] {6,0}); badTiles.add(new int[] {6,1});
        badTiles.add(new int[] {6,6}); badTiles.add(new int[] {6,7}); badTiles.add(new int[] {7,1}); badTiles.add(new int[] {7,6});
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
        int[] bestMove = new int[]{-1, 0};
        long startTime = System.currentTimeMillis();
        Reversi reversiCopy = copyReversi(reversi);
        CheckRulesReversi newRules = new CheckRulesReversi(reversiCopy.getBoard(), player);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {


                if (newRules.checkLegalMove(row, col, player)) {
                    reversiCopy.makeMove(player, row, col);
                    int score = AlphaBeta(reversiCopy, depth - 1, true, player, -99999, 99999, startTime);
                    //int score = MiniMax(reversiCopy, depth, true, player, startTime);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{row, col};
                    }
                }
            }
        }

        System.out.println(System.currentTimeMillis() - startTime);

        return bestMove;
    }

    private static boolean cornerPossible(Reversi reversi, int player) {

        CheckRulesReversi rules = new CheckRulesReversi(reversi.getBoard(), reversi.getPlayer());
        if (rules.checkLegalMove(0,0,player) || rules.checkLegalMove(7,0,player) || rules.checkLegalMove(0,7,player) || rules.checkLegalMove(7,7,player)) {
            return true;
        }

        return false;
    }

    private static int[] checkCorners(Reversi reversi, int player) {
        int[] move = new int[] {0,0};
        CheckRulesReversi rules = new CheckRulesReversi(reversi.getBoard(),reversi.getPlayer());
        if (rules.checkLegalMove(0,0,player)) {move[0] = 0; move[1] = 0;}
        else if (rules.checkLegalMove(0,7,player)) {move[0] = 0; move[1] = 7;}
        else if (rules.checkLegalMove(7,0,player)) {move[0] = 7; move[1] = 0;}
        else if (rules.checkLegalMove(7,7,player)) {move[0] = 7; move[1] = 7;}

        return move;
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

    private static int AlphaBeta(Reversi reversi, int depth, boolean myTurn, int player, int alpha, int beta, double startTime) {
        Reversi reversiCopy = copyReversi(reversi);
        CheckRulesReversi newRules = new CheckRulesReversi(reversiCopy.getBoard(),reversiCopy.getPlayer());

        if (System.currentTimeMillis() > (startTime + 9500) || isTerminalNode(reversiCopy, player) || depth == 0 ) {
            return evaluateBoard(reversiCopy, player);
        }

        int bestScore;
        if (myTurn) {
            bestScore = -99999;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (newRules.checkLegalMove(row,col,player)) {
                        reversiCopy.makeMove(player, row, col);
                        bestScore = Math.max(bestScore,AlphaBeta(reversiCopy, depth - 1, false, player, alpha, beta, startTime));
                        alpha = Math.max(alpha, bestScore);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
        } else {
            bestScore = 99999;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (newRules.checkLegalMove(row,col,player)) {
                        reversiCopy.makeMove(Reversi.getOpponent(player), row, col);
                        bestScore = Math.max(bestScore,AlphaBeta(reversiCopy, depth - 1, true, player, alpha, beta, startTime));
                        beta = Math.min(beta, bestScore);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }

            }
        }
        return bestScore;

    }

    private static int MiniMax(Reversi reversi, int depth, boolean myTurn, int player, long startTime) {
        Reversi reversiCopy = copyReversi(reversi);
        CheckRulesReversi newRules = new CheckRulesReversi(reversiCopy.getBoard(),reversiCopy.getPlayer());

        if (System.currentTimeMillis() > (startTime + 9500) || isTerminalNode(reversiCopy, player) || depth == 0 ) {
            return evaluateBoard(reversiCopy, player);
        }

        int bestScore;
        if (myTurn) {
            bestScore = -99999;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (newRules.checkLegalMove(row,col,player)) {
                        reversiCopy.makeMove(player, row, col);
                        int Score = MiniMax(reversiCopy, depth - 1, false, player, startTime);
                        if (Score > bestScore) {
                            bestScore = Score;
                        }
                    }
                }
            }
        } else {
            bestScore = 99999;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (newRules.checkLegalMove(row,col,Reversi.getOpponent(player))) {
                        reversiCopy.makeMove(Reversi.getOpponent(player), row, col);
                        int Score = MiniMax(reversiCopy, depth - 1, true, player, startTime);
                        if (Score < bestScore) {
                            bestScore = Score;
                        }
                    }
                }
            }
        }
        return bestScore;
    }

    public static int emptySpaces(Board board) {
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

    public static int[] pointsBoardMove(Reversi reversi, int player) {
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

    private int[] NewAI(Reversi reversi, int player) {
        Reversi reversiCopy;
        CheckRulesReversi newRules;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                reversiCopy = copyReversi(reversi);
                newRules = new CheckRulesReversi(reversiCopy.getBoard(),reversiCopy.getPlayer());
                // Checkt of hij de tegenstander kan laten passen
                if (newRules.checkLegalMove(row,col,player)) {
                    reversiCopy.makeMove(player,row,col);
                    if (!reversiCopy.canPlay(Reversi.getOpponent(player))) {
                        return new int[] {row, col};
                    }
                }
            }
        }
        return pointsBoardMove(reversi,player);
    }

    public int[] AIMove(Reversi reversi, int player, int depth) {
        Reversi reversiCopy;
        CheckRulesReversi newRules;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                reversiCopy = copyReversi(reversi);
                newRules = new CheckRulesReversi(reversiCopy.getBoard(),reversiCopy.getPlayer());
                // Checkt of hij de tegenstander kan laten passen
                if (newRules.checkLegalMove(row,col,player)) {
                    reversiCopy.makeMove(player,row,col);
                    if (!reversiCopy.canPlay(Reversi.getOpponent(player))) {
                        return new int[] {row, col};
                    }
                }
            }
        }

        return pointsBoardMove(reversi,player);
        //return bestMove(reversi, depth, player);




    }
}