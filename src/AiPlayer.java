import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AiPlayer implements Player {

    private final String name = "Hal";
    private final String mark = "O";
    public String diffLevel = "";
    Board theGrid = new Board();

    public void setDifficultyLevel(String level) {
        diffLevel = level;
    }

    public int aiMove(String[] grid) {
        theGrid.grid = grid;
        int halsMove = 0;
        System.out.println("The Difficulty Level is set to:  " + diffLevel);

        if (diffLevel.equals("Easy")) {
            //Hals turn and playing with a random generator
            Random rand = new Random();
            int halsPosition = rand.nextInt(9);
            //checking to ensure Hals number is going into a blank spot
            while (grid[halsPosition] != "") {
                halsPosition = rand.nextInt(9);
            }
            return halsPosition;
        } else {
            halsMove = bestMove();
        }
        return halsMove;
    }

    public int bestMove() {
        int bestScore = Integer.MAX_VALUE;
        int bestMove = 0;
        for (int i = 0; i < theGrid.grid.length; i++) {
            if (theGrid.grid[i].equals("")) {
                theGrid.grid[i] = "O";
                int score = miniMax(0, false);
                theGrid.grid[i] = "";
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
        List<String> tieTracker = new ArrayList<>(Arrays.asList(theGrid.grid));
        tieTracker.removeAll(Arrays.asList("", null));
        if (theGrid.isThereAWinner(theGrid.grid, "X")) return 1;
        if (theGrid.isThereAWinner(theGrid.grid, "O")) return -1;
        if (tieTracker.size() == 9) return 0;

        if (isMaximizing) {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < theGrid.grid.length; i++) {
                if (theGrid.grid[i].equals("")) {
                    theGrid.grid[i] = "O";
                    score = miniMax(depth + 1, false);
                    theGrid.grid[i] = "";
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        } else {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < theGrid.grid.length; i++) {
                if (theGrid.grid[i].equals("")) {
                    theGrid.grid[i] = "X";
                    score = miniMax(depth + 1, true);
                    theGrid.grid[i] = "";
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    @Override
    public String getPlayer() {
        return this.name;
    }

    @Override
    public String setMark() {
        return this.mark;
    }
}