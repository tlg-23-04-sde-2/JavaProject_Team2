package com.gojava.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AiPlayerTest {

    AiPlayer player;
    Board theGrid;

    @Before
    public void setUp() {

        player = new AiPlayer();
        theGrid = new Board();
    }

    @Test
    public void aIMove_shouldReturnBlockMove_whenPlayer1IsAboutToWin() {
        String[] grid = {"X", "", "O", "", "X", "", "", "", ""};
        int bestMove = player.aiMove(grid);
        System.out.println("The bestMove is: " + bestMove);
        // The winning move for "X" is grid 8 and "O" should block
        assertEquals(8, bestMove);
    }

    @Test
    public void aIMove_shouldReturnRandomNumber_whenDifficultyIsEasy() {
        String[] grid = {"X", "O", "O", "X", "X", "O", "O", "X", ""};
        player.diffLevel = "Easy";
        int bestMove = player.aiMove(grid);
        System.out.println("The bestMove is: " + bestMove);
        // The only vacant spot on the board is grid 9 and random generator return should only be 8
        assertEquals(8, bestMove);
    }
}