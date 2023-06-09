package com.gojava.tictactoe;

/*
 *   This provides for exactly 3 instances of statuses for the board
 */
enum Status {

    PLAYING("Playing"),
    GAME_OVER("Game Over"),
    TIE("It's A Tie");

    // Everything below here is regular class definition to provide string values of Status Enums
    private final String display;

    // constructor - implicitly private, only called from inside
    Status(String display) {
        this.display = display;
    }

    // accessor methods - here, we provide "read-only" access to the display property
    public String getDisplay() {
        return display;
    }
}