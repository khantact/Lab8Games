import java.util.*;

public class MatchsticksGame {

    // determines whether debug information is displayed for both players
    public static final boolean DEBUG = false;

    private static final int DEFAULT_STARTING_STICKS = 10;

    // The maximum matchsticks a player can choose to take on their turn
    public static final int MAX_STICKS_CHOICE = 3;

    // for use in any classes that need random values
    public static Random rand = new Random();

    private Player[] players = new Player[2]; // stores the two players
    private int startingSticks;

    private static Scanner scan;

    public MatchsticksGame(Player player1, Player player2) {
        this(player1, player2, DEFAULT_STARTING_STICKS);
    }

    public MatchsticksGame(Player player1, Player player2, int startingSticks) {
        scan = new Scanner(System.in);
        if (startingSticks <= 0)
            this.startingSticks = DEFAULT_STARTING_STICKS;
        else
            this.startingSticks = startingSticks;
        this.players[0] = player1;
        this.players[0].init(startingSticks, 1);
        this.players[1] = player2;
        this.players[1].init(startingSticks, 2);

    }

    private void finishGame(int loserIdx) {
        System.out.println("\nPlayer " + (loserIdx + 1) + " (" + players[loserIdx].getPlayerType() + ") loses!\n");
        for (int i = 0; i < players.length; i++)
            players[i].gameOver(i != loserIdx);
    }

    public void playSingleGame() {
        System.out.println("\nStarting game with " + this.startingSticks + " matchsticks!...");
        if (players[0] instanceof HumanPlayer) {
            System.out.println("Enter a name for Player 1");
        } else if (players[1] instanceof HumanPlayer) {
            System.out.println("Enter a name for Player 2");
        }
        String name = scan.nextLine();
        int currentPlayerIdx = rand.nextInt(players.length);
        int sticksRemaining = this.startingSticks;

        while (sticksRemaining > 0) {
            System.out.println("\n\n****  There are " + sticksRemaining + " matchstick(s) remaining!  ****");
            currentPlayerIdx = (currentPlayerIdx + 1) % players.length;
            String currentPlayer = "Player " + (currentPlayerIdx + 1) + " (" + players[currentPlayerIdx].getPlayerType()
                    + ")";
            if (players[currentPlayerIdx] instanceof HumanPlayer) {
                System.out.println("Player " + (currentPlayerIdx + 1) + " (" + name + ")'s turn!");
            } else {
                System.out.println(currentPlayer + "'s turn!");
            }
            if (DEBUG)
                printTurnDebug(currentPlayerIdx);
            int sticksTaken = players[currentPlayerIdx].takeTurn(sticksRemaining);
            if (players[currentPlayerIdx] instanceof HumanPlayer) {
                System.out.println("Player " + (currentPlayerIdx + 1) + " (" + name + ")" + " takes " + sticksTaken
                        + " matchstick(s)!");
            } else {
                System.out.println(currentPlayer + " takes " + sticksTaken + " matchstick(s)!");
            }
            sticksRemaining -= sticksTaken;
        }
        finishGame(currentPlayerIdx);

    }

    private void printTurnDebug(int currentPlayerIdx) {
        System.out.println("======[PLAYER " + (currentPlayerIdx + 1) + " DEBUG]======");
        System.out.println(players[currentPlayerIdx].getDebugInfo());
        System.out.println("============================");
    }

    public static int getValidInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine().trim();
            try {
                int result = Integer.parseInt(input);
                if (result >= min && result <= max)
                    return result;
                System.out.println("Invalid Input! Number out of range!");
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid Input!  Enter a number!");
            }
        }
    }

    public static String getValidInput(String prompt, String[] validInputs) {
        while (true) {
            System.out.print(prompt);
            String input = scan.nextLine().trim();
            if (validInputs == null || contains(input, validInputs))
                return input;
            System.out.println("Invalid Input!  Enter one of the following: " + String.join(", ", validInputs));
        }
    }

    private static boolean contains(String findMe, String[] arr) {
        for (String s : arr) {
            if (s.equalsIgnoreCase(findMe))
                return true;
        }
        return false;
    }

}
