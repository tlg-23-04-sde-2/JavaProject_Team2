import java.util.*;
import java.util.Map;

public class Application {

    static String[] player1Positions = new String[9];
    static String[] halsPositions = new String[9];
    static String halsMark = "O";
    static String player1sMark = "X";



    public static void main(String[] args) {

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";

        //set up for Player 1 to start
        Scanner scanner = new Scanner(System.in);           // reads input from console
        Board board = new Board();
        String currentPlayer = "Player 1";
        String currentStatus = "In-Play";
        board.showBoard(currentPlayer, currentStatus);

        //Over all while loop while the game is in play.  Terminates if Player enters a Q and if there is a Win or Tie
        while (currentStatus.equals("In-Play")) {

            //  Get human players input can only be 1 through 9 or "Q" to quit.
            boolean validInput = false;
            int player1sPosition = 0;

            //While Loop for checking for Player 1's input to be valid and the spot is vacant
            while (!validInput) {
                System.out.printf(ANSI_GREEN + "%78s", "Enter (1-9) to select the grid for your mark or 'Q' to quit: ");
                String input = scanner.nextLine().trim().toUpperCase();
                if (input.equals("Q")) System.exit(0);
                player1sPosition = Integer.parseInt(input) - 1;

                if (input.matches("1|2|3|4|5|6|7|8|9|Q")) {
                    if (input.equals("Q")) {
                        System.exit(0);
                    } else {
                        //checking to ensure the Player's selection isn't already taken
                        while (player1Positions[player1sPosition] != null || halsPositions[player1sPosition] != null) {
                            System.out.printf(ANSI_RED + "%78s", "Position already taken! Enter a vacant position: ");
                            input = scanner.nextLine().trim().toUpperCase();
                            if (input.equals("Q")) System.exit(0);
                            player1sPosition = Integer.parseInt(input) - 1;
                        }
                    }
                    player1Positions[player1sPosition] = player1sMark;
                    board.updateBoard(player1sMark, player1sPosition);
                    board.showBoard("Hal", "In-Play");
                }
                validInput = true;
            }

            //Check after Player 1's turn to see if there is a winner
            if (board.isThereAWinner(board.grid, player1sMark)) {
                board.status = "Game Over";
                board.player = "Player 1";
                board.showBoard(board.player, board.status);
                System.exit(0);
            }

            //Check to see if there is a Tie
            List<String> tieTracker = new ArrayList<>(Arrays.asList(board.grid));
            tieTracker.removeAll(Arrays.asList("", null));

            if (tieTracker.size() == 9) {
                board.status = "Tie";
                board.player = "It's A Tie";
                board.showBoard(board.player, board.status);
                System.exit(0);
            }

            //Hals turn and playing with miniMax algorithm
            int halsPosition = board.bestMove();
            halsPositions[halsPosition] = halsMark;
            board.updateBoard(halsMark, halsPosition);
            board.showBoard("Player 1", "In-Play");



            //Hals turn and playing with a random generator
//                Random rand = new Random();
//                int halsPosition = rand.nextInt(9);
//                //checking to ensure Hals number is going into a blank spot
//                while (player1Positions[halsPosition] != null || halsPositions[halsPosition] != null) {
//                    halsPosition = rand.nextInt(9);
//                }
//                halsPositions[halsPosition] = halsMark;
//                board.updateBoard(halsMark, halsPosition);
//                board.showBoard("Player 1", "In-Play");

            //check for a winner after Hals move
            if (board.isThereAWinner(board.grid, halsMark)) {
                board.updateBoard(halsMark, halsPosition);
                board.status = "Game Over";
                board.player = "Hal";
                board.showBoard(board.player, board.status);
                System.exit(0);

            }
        }
    }
}
