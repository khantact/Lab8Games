import java.util.Arrays;

public class GameLauncher {

    // Controls what type of player for Players 1 and 2
    private static Player Player1Type = new LearningAIPlayer();
    private static Player Player2Type = new HumanPlayer();
    // private static Player Player1Type = new HumanPlayer();
    // private static Player Player2Type = new BasicAIPlayer();

    // accepted inputs when asking users to play again
    private static final String PLAY_AGAIN_YES = "y";
    private static final String PLAY_AGAIN_NO = "n";
    private static final String[] PLAY_AGAIN_CHOICES = { PLAY_AGAIN_YES, PLAY_AGAIN_NO };

    // Initializes a Matchsticks game object
    private static MatchsticksGame initGame(String[] args) {
        int sticks = -1;
        if (args.length > 0) {
            try {
                sticks = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                System.out.println("Non numeric value entered for number of sticks, using default!");
            }
        }
        if (sticks > 0)
            return new MatchsticksGame(Player1Type, Player2Type, sticks);
        else
            return new MatchsticksGame(Player1Type, Player2Type);
    }

    private static void playGame(MatchsticksGame game) {

        String again;
        if (MatchsticksGame.DEBUG) {
            System.out.println("\n\t********************************************");
            System.out.println("\t**           DEBUG MODE ENABLED           **");
            System.out.println("\t********************************************\n");
        }
        do { // keep playing so long as the user wants to continue
            game.playSingleGame();
            again = MatchsticksGame.getValidInput("Do you want to play again (y/n)?: ", PLAY_AGAIN_CHOICES);
        } while (again.equalsIgnoreCase(PLAY_AGAIN_YES));
        System.out.println("Thanks for playing!");

    }

    public static void main(String[] args) {
        MatchsticksGame game = initGame(args);
        playGame(game);
    }

}