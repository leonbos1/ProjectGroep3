package src.test.java.reversi;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import src.main.java.reversi.CheckRulesReversi;
import src.main.java.reversi.Reversi;

import static org.junit.jupiter.api.Assertions.*;

public class CheckRulesReversiTest {


    Reversi reversi = new Reversi(1);
    CheckRulesReversi rules = new CheckRulesReversi(reversi.getBoard(), 1);

    @Test
    public void testcheckLegalUp() {

        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 1, 1, 1, 2, 0, 0, 0},
                {0, 2, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalUp(6, 4, 1));
        assertTrue(rules.checkLegalUp(2, 1, 1));
        assertFalse(rules.checkLegalUp(3,2,1));
        assertFalse(rules.checkLegalUp(0,0,1));

    }

    @Test
    public void testcheckLegalDown() {

        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 1, 1, 1, 2, 2, 0, 0},
                {0, 2, 0, 0, 2, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalDown(2, 3, 1));
        assertTrue(rules.checkLegalDown(3, 5, 1));
        assertFalse(rules.checkLegalDown(2,4,1));
        assertFalse(rules.checkLegalDown(0,0,1));

    }

    @Test
    public void testcheckLegalLeft() {

        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 1, 1, 1, 2, 2, 0, 0},
                {0, 2, 0, 0, 2, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalLeft(4, 6, 1));
        assertTrue(rules.checkLegalLeft(1, 2, 1));
        assertFalse(rules.checkLegalLeft(2,4,1));
        assertFalse(rules.checkLegalLeft(0,0,1));

    }

    @Test
    public void testcheckLegalRight() {

        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 1, 1, 1, 2, 2, 0, 0},
                {0, 2, 0, 0, 2, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalRight(5, 3, 1));
        assertTrue(rules.checkLegalRight(3, 2, 1));
        assertFalse(rules.checkLegalRight(2,4,1));
        assertFalse(rules.checkLegalRight(0,0,1));
    }

    @Test
    public void testcheckLegalLeftUp() {

        int[][] board = new int[][]{
                {1, 1, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 0, 1, 0, 0, 0},
                {0, 0, 2, 0, 0, 2, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 1, 1, 1, 2, 2, 0, 0},
                {0, 2, 0, 0, 2, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalLeftUp(6, 6, 1));
        assertTrue(rules.checkLegalLeftUp(3, 6, 1));
        assertFalse(rules.checkLegalLeftUp(2,4,1));
        assertFalse(rules.checkLegalLeftUp(0,0,1));

    }

    @Test
    public void testcheckLegalLeftDown() {

        int[][] board = new int[][]{
                {1, 1, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 1, 1, 0, 0, 0},
                {0, 0, 2, 0, 0, 2, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 0},
                {0, 1, 1, 1, 2, 2, 0, 0},
                {0, 2, 0, 0, 2, 2, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalLeftDown(1, 6, 1));
        assertTrue(rules.checkLegalLeftDown(3, 6, 1));
        assertFalse(rules.checkLegalLeftDown(1,4,1));
        assertFalse(rules.checkLegalLeftDown(0,0,1));

    }

    @Test
    public void testcheckLegalRightUp() {

        int[][] board = new int[][]{
                {1, 1, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 1, 1, 0, 0, 0},
                {0, 0, 2, 0, 0, 2, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 1},
                {0, 1, 1, 1, 2, 2, 2, 0},
                {0, 2, 0, 0, 2, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalRightUp(3, 1, 1));
        assertTrue(rules.checkLegalRightUp(6, 4, 1));
        assertFalse(rules.checkLegalRightUp(2,4,1));
        assertFalse(rules.checkLegalRightUp(0,0,1));

    }

    @Test
    public void testcheckLegalRightDown() {

        int[][] board = new int[][]{
                {1, 1, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 0, 1, 0, 0, 0},
                {0, 0, 2, 0, 2, 2, 0, 0},
                {0, 2, 0, 2, 1, 2, 0, 0},
                {1, 1, 1, 1, 2, 2, 1, 0},
                {0, 2, 0, 0, 2, 2, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        reversi.setBoardArray(board);

        assertTrue(rules.checkLegalRightDown(2, 4, 1));
        assertTrue(rules.checkLegalRightDown(1, 3, 1));
        assertFalse(rules.checkLegalRightDown(1,4,1));
        assertFalse(rules.checkLegalRightDown(0,0,1));

    }
}
