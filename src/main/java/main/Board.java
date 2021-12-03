package src.main.java.main;

public class Board {
    public int[][] board;
    public int width;
    public int heigth;

    public Board(int width, int heigth) {
        this.board = new int[heigth][width];
        this.width = width;
        this.heigth = heigth;
    }

    public void updateBoard(int player, int row, int col) {
        if (row >= heigth || col >= width || row < 0 || col < 0) {
            System.out.println("Index out of bounds, max size =" +width+ "(given: row="+row+" col="+col+")");
        } else {
            board[row][col] = player;
        }
    }

    public void updateBoardFourRow(int player, int col) {
        for (int i = 0; i < 6; i++) {
            if (board[i][col] != 0) {
                board[i-1][col] = player;
                return;
            }
        }
        board[heigth-1][col] = player;
    }

    public void showBoard() {
        for (int row = 0; row < heigth; row++)
        {
            for (int col = 0; col < width; col++)
            {
                System.out.printf("%2d", board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void clearBoard() {
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int getWidth() {return width;}

    public int getHeigth() {return heigth;}

    public void setBoard(int[][] board) {this.board = board;}

    public int[][] getBoard() {return board;}


}
