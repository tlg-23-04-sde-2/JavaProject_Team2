/*
 *   This provides for exactly 3 instances of Status for the board
 */

enum Status {

    PLAYING("Playing"),
    GAME_OVER("Game Over"),
    TIE("It's a tie");

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

    public String toString() {
        return getDisplay();
    }
}