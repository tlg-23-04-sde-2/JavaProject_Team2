import java.util.*;

public class Application {

    static String ANSI_RED = "\u001B[31m";
    static String ANSI_GREEN = "\u001B[32m";
    static String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {

        //set up for Player 1 to start
        Scanner scanner = new Scanner(System.in);           // reads input from console
        Board board = new Board();

        System.out.printf(ANSI_GREEN + "%76s", "********************************************************\n");
        System.out.printf(ANSI_GREEN + "%86s", "******" + ANSI_BLUE +
                " Welcome to Playing TicTacToe with Hal-9000 " + ANSI_GREEN + "******\n");
        System.out.printf(ANSI_GREEN + "%76s", "********************************************************\n");
        System.out.println();
        System.out.printf(ANSI_GREEN + "%67s", " To get started, first please enter your name: ");
        String playerName = scanner.nextLine().trim();

        // Instantiate new Player1
        Player1 newPlayer = new Player1(playerName, "X");

        // Instantiate new Player 2 which is Hal
        AiPlayer newPlayer2 = new AiPlayer();

        // Select the difficulty level E for Easy or H for Hard
        System.out.printf(ANSI_GREEN + "%87s", "Please enter difficulty level, (" +
                ANSI_BLUE + "E" + ANSI_GREEN + ")asy or (" + ANSI_BLUE + "H" + ANSI_GREEN + ")ard: ");
        String diffLevel = scanner.nextLine().trim().toUpperCase();

        // Check to ensure user only enters and "Ee" or "Hh"
        while (!diffLevel.matches("E|H")) {
            System.out.printf(ANSI_GREEN + "%87s", "Please enter difficulty level, (" +
                    ANSI_BLUE + "E" + ANSI_GREEN + ")asy or (" + ANSI_BLUE + "H" + ANSI_GREEN + ")ard: ");
            diffLevel = scanner.nextLine().trim().toUpperCase();
        }

        if (diffLevel.equals("H")) {
            diffLevel = "Hard";
            newPlayer2.setDifficultyLevel(diffLevel);
        } else {
            diffLevel = "Easy";
            newPlayer2.setDifficultyLevel(diffLevel);
        }

        // getPlayer method to return name of new player
        String currentPlayer = newPlayer.getPlayer();
        String currentStatus = Status.PLAYING.getDisplay();
        String currentLevel = diffLevel;
        board.showBoard(currentPlayer, currentStatus, currentLevel);

        //Over all while loop while the game is in play.  Terminates if Player enters a Q and if there is a Win or Tie
        while (currentStatus.equals(Status.PLAYING.getDisplay())) {

            //  Get human players input can only be 1 through 9 or "Q" to quit.
            boolean validInput = false;

            //While Loop for checking for Player 1's input to be valid and the spot is vacant
            while (!validInput) {
                String input = "";
                while (!input.matches("1|2|3|4|5|6|7|8|9|Q")) {
                    System.out.printf(ANSI_GREEN + "%78s", "Enter (1-9) to select the grid for your mark or 'Q' to quit: ");
                    input = scanner.nextLine().trim().toUpperCase();
                }

                if (input.equals("Q")) System.exit(0);
                // set the players input as the nextMove
                newPlayer.setNextMove((Integer.parseInt(input)) - 1);

                if (input.matches("1|2|3|4|5|6|7|8|9|Q")) {
                    if (input.equals("Q")) {
                        System.exit(0);
                    } else {
                        //checking to ensure the Player's selection isn't already taken
                        while (board.grid[newPlayer.getNextMove()] != "") {
                            if (!input.matches("1|2|3|4|5|6|7|8|9|Q")) {
                                System.out.printf(ANSI_GREEN + "%78s", "Enter (1-9) to select the grid for your mark or 'Q' to quit: ");
                            } else {
                                System.out.printf(ANSI_RED + "%78s", "Position already taken! Enter a vacant position: ");
                            }
                            input = scanner.nextLine().trim().toUpperCase();
                            if (input.equals("Q")) System.exit(0);
                            newPlayer.setNextMove(Integer.parseInt(input));
                        }
                    }
                    board.grid[newPlayer.getNextMove()] = newPlayer.mark;
                    board.updateBoard(newPlayer.mark, newPlayer.getNextMove());
                    board.showBoard(newPlayer2.getPlayer(), Status.PLAYING.getDisplay(), diffLevel);
                }
                validInput = true;
            }

            //Check after Player 1's turn to see if there is a winner
            if (board.isThereAWinner(board.grid, newPlayer.mark)) {
                board.status = Status.GAME_OVER.getDisplay();
                board.player = newPlayer.getPlayer();
                board.showBoard(board.player, board.status, diffLevel);
                System.exit(0);
            }

            //Check to see if there is a Tie
            List<String> tieTracker = new ArrayList<>(Arrays.asList(board.grid));
            tieTracker.removeAll(Arrays.asList("", null));

            if (tieTracker.size() == 9) {
                board.status = Status.TIE.getDisplay();
                board.showBoard(board.player, board.status, diffLevel);
                System.exit(0);
            }

            //Hals turn and playing with miniMax algorithm
            int halsPosition = newPlayer2.aiMove(board.grid);
            board.grid[halsPosition] = newPlayer2.setMark();
            board.updateBoard(newPlayer2.setMark(), halsPosition);
            board.showBoard(newPlayer.getPlayer(), Status.PLAYING.getDisplay(), diffLevel);


            //check for a winner after Hals move
            if (board.isThereAWinner(board.grid, newPlayer2.setMark())) {
                board.updateBoard(newPlayer2.setMark(), halsPosition);
                board.status = Status.GAME_OVER.getDisplay();
                board.player = newPlayer2.getPlayer();
                board.showBoard(board.player, board.status, diffLevel);
                System.exit(0);
            }
        }
    }
}
