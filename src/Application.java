import java.util.*;
import java.util.Map;


public class Application {

    static String ANSI_RED = "\u001B[31m";
    static String ANSI_GREEN = "\u001B[32m";
    static String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args){

        //set up for Player 1 to start
        Scanner scanner = new Scanner(System.in);           // reads input from console
        Board board = new Board();

        System.out.printf(ANSI_GREEN + "%80s\n", "********** Welcome to TicTacToe with Hal-9000 **********\n" + ANSI_RESET);
        System.out.printf(ANSI_GREEN + "%58s", "Please enter your name: ");
        String playerName = scanner.nextLine().trim();

        // Instantiated new Player1
        Player1 newPlayer = new Player1(playerName, "X");
        AiPlayer newPlayer2 = new AiPlayer();


        // getPlayer method to return name of new player
        String currentPlayer = newPlayer.getPlayer();
        String currentStatus = Status.PLAYING.getDisplay();
        board.showBoard(currentPlayer, currentStatus);

        //Over all while loop while the game is in play.  Terminates if Player enters a Q and if there is a Win or Tie
        while (currentStatus.equals(Status.PLAYING.getDisplay())) {

            //  Get human players input can only be 1 through 9 or "Q" to quit.
            boolean validInput = false;

            //While Loop for checking for Player 1's input to be valid and the spot is vacant
            while (!validInput) {
                String input = "";
                while(!input.matches("1|2|3|4|5|6|7|8|9|Q")) {
                    System.out.printf(ANSI_GREEN + "%78s", "Enter (1-9) to select the grid for your mark or 'Q' to quit: ");
                    input = scanner.nextLine().trim().toUpperCase();

                }

                if (input.equals("Q")) System.exit(0);
                // set the players input as the nextMove
                newPlayer.setNextMove(Integer.parseInt(input));


                if (input.matches("1|2|3|4|5|6|7|8|9|Q")) {
                    if (input.equals("Q")) {
                        System.exit(0);
                    } else {
                        //checking to ensure the Player's selection isn't already taken
                        while (board.grid[newPlayer.getNextMove()] != "") {
                            if(!input.matches("1|2|3|4|5|6|7|8|9|Q")) {
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
                    board.showBoard(newPlayer2.getPlayer(), Status.PLAYING.getDisplay());
                }
                validInput = true;
            }

            //Check after Player 1's turn to see if there is a winner

            if (board.isThereAWinner(board.grid, newPlayer.mark)) {
                board.status = Status.GAME_OVER.getDisplay();
                board.player = newPlayer.getPlayer();
                board.showBoard(board.player, board.status);
                System.exit(0);
            }

            //Check to see if there is a Tie
            List<String> tieTracker = new ArrayList<>(Arrays.asList(board.grid));
            tieTracker.removeAll(Arrays.asList("", null));

            if (tieTracker.size() == 9) {
                board.status = Status.TIE.getDisplay();
                board.showBoard(board.player, board.status);
                System.exit(0);
            }

            //Hals turn and playing with miniMax algorithm
            int halsPosition = board.bestMove();
            board.grid[halsPosition] = newPlayer2.setMark();
            board.updateBoard(newPlayer2.setMark(), halsPosition);
            board.showBoard(newPlayer.getPlayer(), Status.PLAYING.getDisplay());



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
            if (board.isThereAWinner(board.grid, newPlayer2.setMark())) {
                board.updateBoard(newPlayer2.setMark(), halsPosition);
                board.status = Status.GAME_OVER.getDisplay();
                board.player = newPlayer2.getPlayer();
                board.showBoard(board.player, board.status);
                System.exit(0);

            }
        }
    }
}
