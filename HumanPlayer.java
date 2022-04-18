import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private ArrayList<Integer> history;

    public void init(int startingSticks, int playerNum) {
        history = new ArrayList<Integer>();
    }

    // Determine the max sticks the human player can take this turn, then
    // prompt them for their choice
    public int takeTurn(int remainingSticks) {
        int maxChoiceThisTurn = Math.min(remainingSticks, MatchsticksGame.MAX_STICKS_CHOICE);
        String prompt = "How many sticks will you take (1 - " + maxChoiceThisTurn + ")?: ";
        int choice = MatchsticksGame.getValidInput(prompt, 1, maxChoiceThisTurn);
        history.add(choice);
        return choice;
    }

    public String getDebugInfo() {
        // return turn history for the current game
        String debug = "Previous Moves: ";
        if (history.size() == 0)
            return debug + "no turns taken yet!";
        for (int i = 0; i < history.size(); i++) {
            debug += "Turn " + (i + 1) + ": " + history.get(i);
            if (i != history.size() - 1) {
                debug += ", ";
            }
        }
        return debug;
    }

    public void gameOver(boolean didPlayerWin) {
        // not implemented yet
    }

    public String getPlayerType() {
        return "Human";
    }

}