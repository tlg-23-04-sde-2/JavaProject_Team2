class Board {

    public static void main(String[] args) {

        //"Test Board Section"
        String player = "HAL 9000";
        Status status = Status.PLAYING;
        String[] grid = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        //Print out the headers
        System.out.println();
        System.out.printf(ANSI_GREEN + "%64s\n", "***************************" + ANSI_RESET);
        System.out.printf(ANSI_GREEN + "%70s\n", "******* " + ANSI_BLUE + "TIC-TAC-TOE" + ANSI_GREEN + " *******");
        System.out.printf(ANSI_GREEN + "%64s\n", "***************************" + ANSI_RESET);

        switch (status) {

            case TIE:{
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_RED + "%s\n", status + ANSI_RESET);
                System.out.printf(ANSI_GREEN + "%23s", "Winner Is: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", "No Winner" + ANSI_RESET);
                break;
            }
            case GAME_OVER: {
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_RED + "%s\n", status + ANSI_RESET);
                System.out.printf(ANSI_GREEN + "%23s", "Winner Is: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", player + ANSI_RESET);
                break;
            }
            case PLAYING: {
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s\n", status + ANSI_RESET);
                System.out.printf(ANSI_GREEN + "%23s", "Player Up: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", player + ANSI_RESET);
                break;
            }
            default:
                break;
        }

        //Print out the board
        System.out.println("");
        System.out.printf(ANSI_GREEN + "%55s", "   |     |    \n");
        System.out.printf(ANSI_BLUE + "%41s" + ANSI_GREEN + "  |  " + ANSI_BLUE + "%1s" +
                ANSI_GREEN + "  |  " + ANSI_BLUE + "%1s\n", grid[0], grid[1], grid[2]);
        System.out.printf(ANSI_GREEN + "%57s", "------+-----+------\n");
        System.out.printf(ANSI_BLUE + "%41s" + ANSI_GREEN + "  |  " + ANSI_BLUE + "%1s" +
                ANSI_GREEN + "  |  " + ANSI_BLUE + "%1s\n", grid[3], grid[4], grid[5]);
        System.out.printf(ANSI_GREEN + "%57s", "------+-----+------\n");
        System.out.printf(ANSI_BLUE + "%41s" + ANSI_GREEN + "  |  " + ANSI_BLUE + "%1s" +
                ANSI_GREEN + "  |  " + ANSI_BLUE + "%1s\n", grid[6], grid[7], grid[8]);
        System.out.printf(ANSI_GREEN + "%55s", "   |     |    \n");
    }
}