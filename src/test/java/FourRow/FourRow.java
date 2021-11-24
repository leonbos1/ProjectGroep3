package src.test.java.FourRow;

import org.junit.jupiter.api.Test;
import src.main.java.FourRow.CheckRulesFourRow;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FourRow {

    src.main.java.FourRow.FourRow fourRow = new src.main.java.FourRow.FourRow(1);
    CheckRulesFourRow rules = new CheckRulesFourRow(fourRow.board, 1);

    @Test
    public void testCheckWinnerDiagonal1() {
        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0,},
                {0, 2, 0, 0, 0, 0, 0,},
                {0, 1, 2, 1, 0, 0, 0,},
                {0, 2, 1, 0, 1, 0, 0,},
                {0, 1, 1, 0, 1, 0, 0,},
                {1, 1, 1, 0, 1, 0, 0,}
        };
        fourRow.setBoardArray(board);

        assertTrue(fourRow.checkWinner(1));
        assertFalse(fourRow.checkWinner(2));

    }

    @Test
    public void testCheckWinnerDiagonal2() {
        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0,},
                {0, 2, 0, 0, 0, 0, 0,},
                {0, 1, 2, 1, 0, 0, 0,},
                {0, 2, 0, 0, 1, 0, 0,},
                {0, 2, 1, 1, 2, 1, 0,},
                {1, 1, 1, 0, 1, 1, 1}
        };
        fourRow.setBoardArray(board);

        assertTrue(fourRow.checkWinner(1));
        assertFalse(fourRow.checkWinner(2));

    }

    @Test
    public void testCheckWinnerUp() {
        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0,},
                {0, 2, 0, 0, 0, 0, 0,},
                {0, 1, 2, 1, 0, 1, 0,},
                {0, 2, 0, 2, 0, 1, 0,},
                {0, 2, 1, 1, 0, 1, 0,},
                {1, 0, 1, 0, 1, 1, 0}
        };
        fourRow.setBoardArray(board);

        assertTrue(fourRow.checkWinner(1));
        assertFalse(fourRow.checkWinner(2));

    }

}
