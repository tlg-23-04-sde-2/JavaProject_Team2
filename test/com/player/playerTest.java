package com.player;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class playerTest {

    @Before
    public void setUp() throws Exception {

    }

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
    public void playerX_halsTurnShould_beFalse() {
        Player1 playerX = new Player1("Gali", "O", 5);
        assertFalse(playerX.halsTurn());
    }

    @Test(expected = InvalidInputException.class)
    public void playerX_shouldThrow_invalidInput_lowerBound() throws InvalidInputException {
        Player1 playerX = new Player1("Gali", "O", -1);
        playerX.getNextMove();
    }

    @Test(expected = InvalidInputException.class)
    public void playerX_shouldThrow_invalidInput_upperBound() throws InvalidInputException {
        Player1 playerX = new Player1("Gali", "O", 10);
        playerX.getNextMove();
    }
}