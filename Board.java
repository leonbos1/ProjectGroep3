public class Board {
    public int[][] board;
    public int size;

    public Board() {
        this.board = new int[3][3];
        this.size = 3;
    }

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
    }
}
