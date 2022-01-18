package src.main.java.reversi;
import src.main.java.main.Board;
import java.util.ArrayList;

import java.util.Random;



public class ReversiAI {

    long start;
    long end;
    int timeOutTimer;
    int maxTreeDepth = 10;

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

        int[][] points = pointsForEachMove(reversiCopy, player, depth, player);

        ArrayList<int[]> moves = reversi.possibleMoves(player);
        int maxRow = moves.get(0)[0];
        int maxCol = moves.get(0)[1];
        int maxscore = points[maxRow][maxCol];

        for (int[] i : moves) {
            if (maxscore < points[i[0]][i[1]]) {
                maxscore = points[i[0]][i[1]];
                maxRow = i[0];
                maxCol = i[1];
            }
        }

        return new int[]{maxRow, maxCol};

    }

    public int[][] pointsForEachMove(Reversi reversi, int player, int depth, int playerTurn) {
        int[][] points = new int[8][8];
        int maxScore = -10000;

        rules = new CheckRulesReversi(reversi.board, player);

        if (depth == 0) {
            return points;
        }

        ArrayList<int[]> moves = reversi.possibleMoves(player);
        //int[] scores = new int[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            int[] move = moves.get(i);
            Reversi tryGame = new Reversi(reversi.getPlayer());
            tryGame.setBoardArray(reversi.getBoardArray());
            tryGame.makeMove(playerTurn, move[0], move[1]);

            int[][] nextMove = pointsForEachMove(tryGame, player, depth - 1, Reversi.getOpponent(playerTurn));
            int max = -100000;
            for (int[] arr : nextMove) {
                for (int val : arr) {
                    if (val != 0) {
                        if (max < val) {
                            max = val;
                        }
                    }
                }
            }
            if (max == -100000) {
                max = 0;
            }

            if (playerTurn == player) {
                points[move[0]][move[1]] = max + pointsBoard[move[0]][move[1]];
            } else {
                points[move[0]][move[1]] = max + (pointsBoard[move[0]][move[1]] * -1);
            }
        }
        return points;

    }

        /*
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

         */


    int heuristic(Reversi board, int playerTurn) {

        int ourScore = board.playerScore(playerTurn);
        int opponentScore = board.playerScore(board.getOpponent(playerTurn));
        return (ourScore - opponentScore);
    }

    Reversi copyBoard(Reversi board) {
        Reversi tryGame = new Reversi(board.getPlayer());
        tryGame.setBoardArray(board.getBoardArray());

        return tryGame;
    }

    public int[] minimaxDecision(Reversi board, int playerTurn) {

        start = System.currentTimeMillis();
        end = start + (9 * 1000L);
        int[] bestMove = {0, 0};
        ArrayList<int[]> moves = board.possibleMoves(player);

        if (moves.size() == 0) {
            int x = -1;
            int y = -2;
        } else {
            int bestMoveValue = -99999;

            for (int[] move : moves) {
                Reversi tempBoard = copyBoard(board);
                tempBoard.makeMove(playerTurn, move[0], move[1]);
                int value = minimaxValue(tempBoard, playerTurn, board.getOpponent(playerTurn), 1);

                if (value > bestMoveValue) {
                    bestMoveValue = value;
                    bestMove = move;
                }
            }
        }
        return bestMove;

    }

    int minimaxValue(Reversi board, int originalTurn, int currentTurn, int depth) {


        if (System.currentTimeMillis() > end) {
            return heuristic(board, originalTurn);
        }
        if (depth >= maxTreeDepth) {
            return heuristic(board, originalTurn);
        }

        int bestMoveValue = 0;
        ArrayList<int[]> moves = board.possibleMoves(player);

        if (moves.size() == 0) {
            return minimaxValue(board, originalTurn, currentTurn, depth + 1);
        } else {
            bestMoveValue = -99999;
            if (originalTurn != currentTurn) {
                bestMoveValue = 99999;
            }

            for (int[] move : moves) {
                Reversi tempBoard = copyBoard(board);
                makeMinimaxMove(tempBoard, move, currentTurn);
                int value = minimaxValue(tempBoard, originalTurn, board.getOpponent(currentTurn), depth + 1);

                if (originalTurn == currentTurn) {
                    if (value > bestMoveValue) {
                        bestMoveValue = value;
                    } else {
                        if (value < bestMoveValue) {
                            bestMoveValue = value;
                        }
                    }
                }
            }
        }
        return bestMoveValue;

    }



    public void makeMinimaxMove(Reversi board, int[] move, int playerTurn) {
        int col = move[0];
        int row = move[1];
        board.makeMove(playerTurn, row, col);
        int[] moveToMake = {col, row};
    }

    //public int[] makeAIMove(Reversi reversi) {
    //    minimaxDecision(reversi, player);

    //}





/*
        System.out.println(depth);
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                System.out.printf("%5d", points[row][col]);
            }
            System.out.println();
        }
        System.out.println();
        return points;
*/

}