package com.gojava.tictactoe;

/*
 *  Creating the human player
 */

public class Player1 implements Player {

    // Fields (constants)
    public String name;
    public String mark = "";
    public int nextMove = 0;
    public int lowerBound = 0;
    public int upperBound = 10;

    // Constructors
    public Player1(String name, String mark) {
        setName(name);
        setMark(mark);
    }

    public Player1(String name, String mark, int nextMove) {
        setName(name);
        setMark(mark);
        setNextMove(nextMove);
    }

    // Accessor methods
    public void setName(String name) {
        this.name = name;
    }

    public void setMark(String mark) {
        this.mark = mark;

    }

    public void setNextMove(int nextMove) {
        if (nextMove == lowerBound) {
            this.nextMove = nextMove;
        }
        if (nextMove >= lowerBound && nextMove <= upperBound) {
            this.nextMove = nextMove;
        }
    }

    public int getNextMove() {
        return nextMove;
    }

    // Interface Methods
    @Override
    public String getPlayer() {
        return this.name;
    }

    // Only need to retrieve once at the beginning to note what mark("X" or "O") LiveActionPlayer is
    @Override
    public String setMark() {
        return this.mark;
    }
}