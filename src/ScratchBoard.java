import java.util.*;

class ScratchBoard {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> halPositions = new ArrayList<Integer>();


    public static void main(String[] args) {

//        TicTacToe tictactoe = new TicTacToe();
        boolean isValid;


        String player = "LiveActionPlayer";
        String status = "In-Play";
        String[] grid = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";

        //Call to the extracted method to test it
        run(player + ANSI_RESET, status, grid, ANSI_BLUE, ANSI_GREEN, ANSI_RED, ANSI_RESET);
        return;
    }
    //extracted method to be able to run the board
    //most likely will have to change based on new board configuration
    private static void run(String s, String status, String[] grid, String ANSI_BLUE, String ANSI_GREEN, String ANSI_RED, String ANSI_RESET) {
        System.out.println();
        System.out.printf(ANSI_GREEN + "%64s\n", "***************************" + ANSI_RESET);
        System.out.printf(ANSI_GREEN + "%70s\n", "******* " + ANSI_BLUE + "TIC-TAC-TOE" + ANSI_GREEN + " *******");
        System.out.printf(ANSI_GREEN + "%64s\n", "***************************" + ANSI_RESET);

        switch (status) {
            case "Game Over": {
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_RED + "%s\n", status + ANSI_RESET);
                System.out.printf(ANSI_GREEN + "%23s", "Winner Is: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", s);
                break;
            }
            default: {
                System.out.printf(ANSI_GREEN + "%23s", "Game Status: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s\n", status + ANSI_RESET);
                System.out.printf(ANSI_GREEN + "%23s", "Player Up: " + ANSI_RESET);
                System.out.printf(ANSI_BLUE + "%s", s);
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


        //below is the logic of prompting the user to place their mark ("X")
        //ensures no overlap between computer and LiveActionPlayer

        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Where would you like to place your mark (1-9)?");
            int pos = scan.nextInt();
            while(playerPositions.contains(pos) || halPositions.contains(pos)){
                System.out.println("Already marked!  Try again.");
                pos = scan.nextInt();
            }

            System.out.println(pos);

            placeMark(grid, pos, "LiveActionPlayer");
            String result = checkWinner();

            Random rand = new Random();
            int halPos = rand.nextInt(grid.length) + 1;
            while(playerPositions.contains(halPos) || halPositions.contains(halPos)){
                halPos = rand.nextInt(grid.length) +1;
            }

            placeMark(grid, halPos, "HAL9000");

            //print out the board again with the user input now added

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

            result = checkWinner();
            System.out.println(result);
        }
    }

    //method for placing marks
    public static void placeMark(String[] grid, int pos, String user) {
        String mark = " ";

        if (user.equals("LiveActionPlayer")) {
            mark = "O";
            playerPositions.add(pos);
        } else if (user.equals("HAL9000")) {
            mark = "X";
            halPositions.add(pos);
        }
        switch (pos) {
            case 1:
                grid[0] = mark;
                break;
            case 2:
                grid[1] = mark;
                break;
            case 3:
                grid[2] = mark;
                break;
            case 4:
                grid[3] = mark;
                break;
            case 5:
                grid[4] = mark;
                break;
            case 6:
                grid[5] = mark;
                break;
            case 7:
                grid[6] = mark;
                break;
            case 8:
                grid[7] = mark;
                break;
            case 9:
                grid[8] = mark;
                break;
            default:
                break;
        }

    }
    //method to check winner
    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftColumn = Arrays.asList(1,4,7);
        List middleColumn = Arrays.asList(2,5,8);
        List rightColumn = Arrays.asList(3,6,9);
        List leftCross = Arrays.asList(1,5,9);
        List rightCross = Arrays.asList(3,5,7);

        List<List> win = new ArrayList<List>();
        win.add(topRow);
        win.add(middleRow);
        win.add(bottomRow);
        win.add(leftColumn);
        win.add(middleColumn);
        win.add(rightColumn);
        win.add(leftCross);
        win.add(rightCross);

        for(List l : win) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations, you won!";

            }
            else if (halPositions.containsAll(l)){
                return "I'm sorry, Dave";
            }
            else if (playerPositions.size() + halPositions.size() == 9) {
                return "Tie";
            }
        }
        return"";
    }
}