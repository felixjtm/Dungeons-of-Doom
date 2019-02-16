import java.util.Scanner;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 * Extents the Player class
 */
public class HumanPlayer extends Player{

    //Declares variable for reading user input
    private Scanner reader;

    //Stores user input
    private String input;

    //Stores the player's gold
    private int gold = 0;

    /**
     * Constructor that initialises variables for the player
     */
    public HumanPlayer() {
        reader = new Scanner(System.in);
        playerChar = 'P';
    }

    /**
     * @return : The player's gold
     */
    protected int getGold() {
        return gold;
    }

    /**
     * Increments the player's gold count by 1
     */
    protected void incrementGold() {
        gold++;
    }

    /**
     * Outputs the result of the player's action
     *
     * @param result : The result of the action
     */
    @Override
    protected void handleResult(String result) {
        System.out.println(result);
    }

    /**
     * Gets the user to input the next action for the player to take
     *
     * @return : The next action for the player that has been input by the user
     */
    @Override
    public String getNextAction() {
        //Checks to make sure there is an input
        if (reader.hasNextLine()) {
            input = reader.nextLine();
            return input;
        }
        return "";
    }

}