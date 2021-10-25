import java.util.Scanner;

public class Board {
    public int[][] board;
    public int size;
    public int win_length;

    public Board() {
        Scanner scanner = new Scanner(System.in);
        this.size = Integer.parseInt(scanner.nextLine());
        this.board = new int[size][size];
        System.out.print("Wat is de benodigde lengte om te winnen?:");
        this.win_length = Integer.parseInt(scanner.nextLine());
    }

    public Board(int size, int win_length) {
        this.board = new int[size][size];
        this.size = size;
        this.win_length = win_length;
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
    }

    public void setSize(int size) {this.size = size;}

    public int getSize() {
        return size;
    }

    public void setBoard(int[][] board) {this.board = board;}

    public int[][] getBoard() {return board;}


}
