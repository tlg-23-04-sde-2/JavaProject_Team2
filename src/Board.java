import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    String[] grid = {"", "", "", "", "", "", "", "", ""};
    String player = "";
    String status = "In-Play";


    // CONSTRUCTORS - this one is private, to prevent outside instantiation (only I can create new)
    public Board() {

    }

    public void updateBoard(String theMark, int position) {
        grid[position] = theMark;
    }

    public boolean isThereAWinner(String[] grid, String mark) {

        if (
                (grid[0].equals(mark) && grid[1].equals(mark) && grid[2].equals(mark)) ||
                        (grid[3].equals(mark) && grid[4].equals(mark) && grid[5].equals(mark)) ||
                        (grid[6].equals(mark) && grid[7].equals(mark) && grid[8].equals(mark)) ||
                        (grid[0].equals(mark) && grid[3].equals(mark) && grid[6].equals(mark)) ||
                        (grid[1].equals(mark) && grid[4].equals(mark) && grid[7].equals(mark)) ||
                        (grid[2].equals(mark) && grid[5].equals(mark) && grid[8].equals(mark)) ||
                        (grid[0].equals(mark) && grid[4].equals(mark) && grid[8].equals(mark)) ||
                        (grid[2].equals(mark) && grid[4].equals(mark) && grid[6].equals(mark))) {
            return true;
        } else {
            return false;
        }
    }

    //Implementing miniMax for Hal's move

    public int bestMove() {
        int bestScore = Integer.MAX_VALUE;
        int bestMove = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i].equals("")) {
                grid[i] = "O";
                int score = miniMax(0, false);
                grid[i] = "";
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    public int miniMax(int depth, boolean isMaximizing) {
        int bestScore = 0;
        int score = 0;
        List<String> tieTracker = new ArrayList<>(Arrays.asList(grid));
        tieTracker.removeAll(Arrays.asList("", null));
        if (isThereAWinner(grid, "X")) return 1;
        if (isThereAWinner(grid, "O")) return -1;
        if (tieTracker.size() == 9) return 0;

        if (isMaximizing){
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < grid.length; i++){
                if (grid[i].equals("")) {
                    grid[i] = "O";
                    score = miniMax(depth + 1, false);
                    grid[i] = "";
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        } else {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < grid.length; i++){
                if (grid[i].equals("")) {
                    grid[i] = "X";
                    score = miniMax(depth + 1, true);
                    grid[i] = "";
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        }
    }


    public void showBoard(String thePlayer, String theStatus) {

        String player = thePlayer;
        String status = theStatus;

        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_CLEAR = "\033[H\033[2J";

        //Print out the headers
        System.out.printf(ANSI_CLEAR);
        System.out.println();
        System.out.printf(ANSI_GREEN + "%64s\n", "***************************" + ANSI_RESET);
        System.out.printf(ANSI_GREEN + "%70s\n", "******* " + ANSI_BLUE + "TIC-TAC-TOE" + ANSI_GREEN + " *******");
        System.out.printf(ANSI_GREEN + "%64s\n", "***************************" + ANSI_RESET);

        switch (status) {
            case "Game Over": {
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_RED + "%s", status + ANSI_RESET);
                System.out.printf(ANSI_GREEN + "%55s", "Winner Is: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", player + ANSI_RESET);
                break;
            }
            case "Tie": {
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_RED + "%s", status + ANSI_RESET);
                //System.out.printf(ANSI_GREEN + "%23s", "Winner Is: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%55s", player + ANSI_RESET);
                break;
            }
            default: {
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", status + ANSI_RESET);
                System.out.printf(ANSI_GREEN + "%55s", "Player Up: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", player + ANSI_RESET);
            }
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
        System.out.println();
    }
}