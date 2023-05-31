package com.gojava.tictactoe;

public class Player1 implements Player {

    // fields ( constants )
    public String name;
    public String mark = "O";
    public int nextMove = 0;
    public int lowerBound = 1;
    public int upperBound = 9;

    // constructors
    public Player1(String name, String mark) {
        setName(name);
        setMark(mark);
    }

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

        if (nextMove == lowerBound) {
            this.nextMove = nextMove;
        }
        if (nextMove >= lowerBound && nextMove <= upperBound) {
            this.nextMove = nextMove;
        }
    }

    // need to code throwing of error message if next move choice is out of bounds
    public int getNextMove() {
        return nextMove;
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
}