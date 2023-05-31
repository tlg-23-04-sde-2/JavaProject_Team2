import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class playerTest {

    Player1 playerX;

    @Before

    public void setUp() {
        playerX = new Player1("Gali", "O", 5);
    }

    @Test
    public void playerX_nameShould_beSet() {
        assertEquals("Gali", playerX.getPlayer());
    }

    @Test
    public void playerX_markShould_beSet() {
        assertEquals(5, playerX.getNextMove());
    }

    @Test
    public void shouldReturn_nextMove() {
        playerX.setNextMove(1);
        System.out.println(playerX.getNextMove());
    }

    @Test
    public void playerX_halsTurnShould_beFalse() {
        assertFalse(playerX.halsTurn());
    }

    @Test
    public void playerX_shouldThrow_invalidInput_lowerBound() {
        playerX.setNextMove(-1);
        assertEquals(5, playerX.getNextMove());
    }

    @Test
    public void playerX_shouldThrow_invalidInput_upperBound() {
        playerX.setNextMove(10);
        assertEquals(5, playerX.getNextMove());
    }
}