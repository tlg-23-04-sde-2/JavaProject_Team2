package com.player;

import com.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Player1 implements Player {

    // fields ( constants )
    public String name;
    public String mark = "O";
    public int nextMove;
    public int lowerBound = 1;
    public int upperBound = 9;



    // constructors


    public Player1(String name, String mark, int nextMove) {
        setName(name);
        setMark(mark);
        setNextMove(nextMove);
    }



    // accessor methods
    public void setName(String name) {
        this.name = name;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }


    // need to code throwing of error message if next move choice is out of bounds
    public int getNextMove() throws InvalidInputException{

        if ( nextMove >= lowerBound && nextMove <= upperBound ) {
            return nextMove;
        } else {
            throw new InvalidInputException("Invalid Input. Please choose a value between " + lowerBound + " - " + upperBound);
        }
    }






    // Interface Methods

    @Override
    public String getPlayer() {
        return this.name;
    }

    // only need to retrieve once at beginning to note what Mark( X or O ) LiveActionPlayer is
    @Override
    public String setMark() {
        return this.mark;
    }

    // needs to return TRUE when user is prompted to input next coordinates and return FALSE while AiPlayer is generating next move
    @Override
    public boolean halsTurn() {
        return false;
    }
}