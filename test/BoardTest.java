import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void UpdateBoard_shouldReturnMark_whenPassedToValidGridPosition() {
        board.updateBoard("X", 3);
        assertEquals("X", board.grid[3]);
    }

    @Test
    public void isThereAWinner_shouldReturnTrue_whenAWinningCombinationIsPassedIn() {
        String[] winning = {"X", "X", "X", "", "", "", "", "", "", ""};
        assertTrue(board.isThereAWinner(winning, "X"));
    }

    @Test
    public void isThereAWinner_shouldReturnFalse_whenANonWinningCombinationIsPassedIn() {
        String[] loosing = {"X", "X", "O", "", "", "", "", "", "", ""};
        assertFalse(board.isThereAWinner(loosing, "X"));
    }

}