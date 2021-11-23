package src.main.java.main;

import java.util.Scanner;

public class Board {
    public int[][] board;
    public int size;

    public Board(int size) {
        this.board = new int[size][size];
        this.size = size;
    }

    public void updateBoard(int player, int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            System.out.println("Index out of bounds, max size =" +size+ "(given: row="+row+" col="+col+")");
        } else {
            board[row][col] = player;
        }
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
