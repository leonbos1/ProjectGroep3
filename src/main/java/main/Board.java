package src.main.java.main;

import java.util.Scanner;

public class Board {
    public int[][] board;
    public int size;

    public Board(int width, int heigth) {
        this.board = new int[heigth][width];
        this.size = width;
    }

    public void updateBoard(int player, int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            System.out.println("Index out of bounds, max size =" +size+ "(given: row="+row+" col="+col+")");
        } else {
            board[row][col] = player;
        }
    }

    public void updateBoardFourRow(int player, int col) {
        for (int i = 0; i < 7; i++) {
            if (board[i][col] != 0) {
                board[i-1][col] = player;
                return;
            }
        }
        board[board.length-1][col] = player;
    }

    public void showBoard() {
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                System.out.printf("%2d", board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void clearBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void setSize(int size) {this.size = size;}

    public int getSize() {return size;}

    public void setBoard(int[][] board) {this.board = board;}

    public int[][] getBoard() {return board;}


}
