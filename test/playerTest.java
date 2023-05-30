import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class playerTest {


    @Test
    public void playerX_nameShould_beSet() throws InvalidInputException{
        Player1 playerX = new Player1("Gali", "O", 5);
        assertEquals("Gali", playerX.getPlayer());
    }

    @Test
    public void playerX_markShould_Beset() throws InvalidInputException{
        Player1 playerX = new Player1("Gali", "O", 5);
        assertEquals(5, playerX.getNextMove());
    }

    @Test
    public void shouldReturn_nextMove() throws InvalidInputException {
        Player1 playerX = new Player1("Gali", "O", 5);
        playerX.setNextMove(1);
        System.out.println(playerX.getNextMove());
    }

    @Test
    public void playerX_halsTurnShould_beFalse() throws InvalidInputException {
        Player1 playerX = new Player1("Gali", "O", 5);
        assertFalse(playerX.halsTurn());
    }

    @Test(expected = InvalidInputException.class)
    public void playerX_shouldThrow_invalidInput_lowerBound() throws InvalidInputException {
        Player1 playerX = new Player1("Gali", "O", 0);
        playerX.getNextMove();
    }

    @Test(expected = InvalidInputException.class)
    public void playerX_shouldThrow_invalidInput_upperBound() throws InvalidInputException {
        Player1 playerX = new Player1("Gali", "O", 1);
        playerX.setNextMove(-1);
        playerX.getNextMove();
    }
}