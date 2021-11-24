package src.test.java.FourRow;

import org.junit.jupiter.api.Test;
import src.main.java.FourRow.CheckRulesFourRow;
import src.main.java.FourRow.FourRow;
import src.main.java.reversi.CheckRulesReversi;
import src.main.java.reversi.Reversi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckRulesFourRowTest {

    FourRow fourRow = new FourRow(1);
    CheckRulesFourRow rules = new CheckRulesFourRow(fourRow.board, 1);

    @Test
    public void testcheckLegalMove() {

        int[][] board = new int[][]{
                {0, 1, 0, 0, 0, 0, 0,},
                {0, 2, 0, 0, 0, 0, 0,},
                {0, 1, 2, 0, 0, 0, 0,},
                {0, 2, 2, 2, 1, 0, 0,},
                {0, 1, 1, 1, 2, 0, 0,},
                {0, 1, 1, 1, 1, 0, 0,}
        };
        fourRow.setBoardArray(board);

        assertTrue(rules.checkLegalMove(1));
        assertTrue(rules.checkLegalMove(3));
        assertFalse(rules.checkLegalMove(1));

    }

}
