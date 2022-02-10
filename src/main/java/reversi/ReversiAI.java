package src.main.java.reversi;
import src.main.java.main.Board;

import java.util.*;


public class ReversiAI {

    private static final Integer[][] pointsBoard = new Integer[][]{
            {500, -100, 100, 100, 100, 100, -100, 500},
            {-100, -200, -50, -50, -50, -50, -200, -100},
            {100, -50, 100, 75, 75, 100, -50, 100},
            {100, -50, 75, 0, 0, 75, -50, 100},
            {100, -50, 75, 0, 0, 75, -50, 100},
            {100, -50, 100, 75, 75, 100, -50, 100},
            {-100, -200, -50, -50, -50, -50, -200, -100},
            {500, -100, 100, 100, 100, 100, -100, 500}
    };

    Board board;
    int player;
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


    private static int evaluateBoard(int[][] board, int player) {
        int player_points = 0;
        int opponent_points = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == player)
                    player_points++;
                else if (board[row][col] == Reversi.getOpponent(player))
                    opponent_points++;
            }
        }

        return (player_points - opponent_points);

    }

    private static boolean isTerminalNode(int[][] board, int player) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (AIStatic.checkLegalMove(board, row, col, player)) {
                    return false;
                }
            }
        }
        return true;

    }


    public static int emptySpaces(int[][] board) {
        int emptySpaces = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == 0) {
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

    public static Integer[] pointsBoardMove(Reversi reversi, int player) {
        int highestScore = -1000;
        Integer[] highestPosition = new Integer[]{-1, -1};
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

    public static int[][] boardCopy(int[][] board) {
        int[][] newboard = new int[board.length][board[0].length];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newboard[i][j] = board[i][j];
            }
        }
        return newboard;
    }

    private static int AlphaBeta(int[][] board, int depth, boolean myTurn, int player,int alpha, int beta, double startTime) {

        if (System.currentTimeMillis() > (startTime + 9500) || isTerminalNode(board, player)  || depth == 0) {
            return evaluateBoard(board,player);
        }
        int[][] board2;
        int bestScore;
        if (myTurn) {
            bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (AIStatic.checkLegalMove(board,row, col, player)) {
                        board2 = boardCopy(board);
                        board2 = AIStatic.makeMove(board2, player, row, col);
                        bestScore = Math.max(bestScore, AlphaBeta(board2, depth - 1, false, player, alpha, beta, startTime));
                        alpha = Math.max(alpha, bestScore);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (AIStatic.checkLegalMove(board, row, col, Reversi.getOpponent(player))) {
                        board2 = boardCopy(board);
                        board2 = AIStatic.makeMove(board2, Reversi.getOpponent(player), row, col);
                        bestScore = Math.min(bestScore, AlphaBeta(board2, depth - 1, true, player, alpha, beta, startTime));
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

    private static Integer[] bestMove(int[][] board, int depth, int player) {
        int[][] pointBoard = {
                {1000, -10, 20, 20, 20, 20, -1000, 1000},
                {-1000, -50, -2, -2, -2, -2, -50, -1000},
                {20, -2, -1, -1, -1, -1, -2, 20},
                {20, -2, -1, -1, -1, -1, -2, 20},
                {20, -2, -1, -1, -1, -1, -2, 20},
                {20, -2, -1, -1, -1, -1, -2, 20},
                {-1000, -50, -2, -2, -2, -2, -50, -1000},
                {1000, -1000, 20, 20, 20, 20, -1000, 1000}
        };

        if (board[0][0] == player) {
            pointBoard[0][1] = 10;
            pointBoard[1][7] = 10;
        }
        if (board[0][7] == player) {
            pointBoard[0][6] = 10;
            pointBoard[1][7] = 10;
        }
        if (board[7][0] == player) {
            pointBoard[6][0] = 10;
            pointBoard[7][1] = 10;
        }
        if (board[7][7] == player) {
            pointBoard[7][6] = 10;
            pointBoard[6][7] = 10;
        }

        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        HashMap<Integer[], Integer> movePoints = new HashMap<>();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int x = row;
                int y = col;
                if (AIStatic.checkLegalMove(board,row,col,player)) {
                    Thread thread = new Thread(() -> {
                        int[][] newBoard = boardCopy(board);
                        newBoard = AIStatic.makeMove(newBoard, player, x, y);

                        int points = AlphaBeta(newBoard, depth, true, player, Integer.MIN_VALUE, Integer.MAX_VALUE, startTime);

                        points += pointBoard[x][y];
                        movePoints.put(new Integer[]{x, y}, points);
                    });
                    threads.add(thread);
                    thread.start();
                }
            }
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer[] bestMove = new Integer[] {-1, -1};
        int bestPoints = Integer.MIN_VALUE;
        Iterator<Map.Entry<Integer[], Integer>> it = movePoints.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer[], Integer> entry = it.next();
            Integer[] move = entry.getKey();
            int points = entry.getValue();
            if (points > bestPoints) {
                bestPoints = points;
                bestMove = move;
            }
        }

        return bestMove;
    }

    public Integer[] AIMove(Reversi reversi, int player, int depth) {
        Reversi reversi1 = copyReversi(reversi);
        int[][] board = reversi1.getBoardArray();
        if (emptySpaces(reversi.getBoardArray()) < 50) {
            return bestMove(board, depth, player);
        }
        return pointsBoardMove(reversi1, player);

    }




}