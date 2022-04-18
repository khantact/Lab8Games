import java.util.ArrayList;

public class LearningAIPlayer implements Player {
    private int[] history;
    private ArrayList<Bucket> buckets = new ArrayList<>();

    public void init(int startingSticks, int playerNum) {
        history = new int[startingSticks + 1];
        int bcounter = 0;
        // Adds marbles into respective buckets
        for (int i = 1; i <= startingSticks; i++) {
            buckets.add(new Bucket(i));
            for (int j = 1; j <= Math.min(3, i); j++) {
                buckets.get(bcounter).addMarble(j);
            }
            bcounter++;
        }

    }

    public int takeTurn(int remainingSticks) {
        int rmarble = buckets.get(remainingSticks - 1).takeRandomMarble();
        history[remainingSticks] = rmarble;
        return rmarble;
    }

    public String side() {
        String onSide = "Marbles on the Side: ";
        for (int i = 0; i < history.length; i++) {
            if (history[i] != 0) {
                onSide += "{" + i + " : " + history[i] + "}, ";
            }
        }
        return onSide;
    }

    public String getDebugInfo() {
        // Print turn history for the current game
        String debug = "";
        for (int i = 0; i < buckets.size(); i++) {
            debug += buckets.get(i).toString() + " ";
        }
        return debug + "\n" + side();
    }

    public void gameOver(boolean didPlayerWin) {
        // clear the history for the next game
        if (didPlayerWin) {
            for (int i = 1; i < history.length; i++) {
                if (history[i] != 0) {
                    buckets.get(i - 1).addMarble(history[i]);
                    buckets.get(i - 1).addMarble(history[i]);
                }
            }
        } else if (!didPlayerWin) {
            for (int i = 0; i < buckets.size(); i++) {
                if (buckets.get(i).getMarbleCount() == 0) {
                    buckets.get(i).addMarble(history[i + 1]);
                }
            }
        }
        history = new int[history.length];
    }

    public String getPlayerType() {
        return "Learning AI";
    }
}
