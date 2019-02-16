import java.util.*;

/**
 * Runs the game with a bot player and contains code needed find and catch the player
 * Extents the Player class
 */
public class BotPlayer extends Player  {

    //Stores whether or not the last command was look
    boolean didLook;

    //Declares the variable for generating random numbers
    Random rand;

    //Stores the next movements for the player
    Stack<String> movement;

    //Stores whether or not the player was seen after a look command
    boolean playerFound;

    /**
     * Constructor that instantiates variables for the player
     */
    public BotPlayer() {
        rand = new Random();
        playerChar = 'B';
        didLook = false;
        playerFound = false;
        movement = new Stack<>();
    }

    /**
     * Calculates the moves that the bot should take in order to reach the player, if the player is visible
     *
     * @param area : 5x5 grid
     * @return
     */
    public void calculateTurn(String area) {
        //Stores the difference between the x and y coordinates of the bot and the human player
        int diffX;
        int diffY;

        //Stores the String area in an array so that it can be easily split into a char array
        String splitArea[];

        //Splits the area based on new lines
        splitArea = area.split("\n");

        //Stores the area in a two dimensional char array so that it can be easily searched
        char charArea[][] = new char[5][];
        for (int i = 0; i < 5; i++) {
            charArea[i] = splitArea[i].toCharArray();
        }

        //Searches the area for the player
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //If the human player is at this point then get the difference between the bot and the human player
                if (charArea[j][i] == 'P') {
                    //The player has been found
                    playerFound = true;

                    //Gets the difference between the bot location and the player location
                    diffX = i - 2;
                    diffY = j - 2;

                    //Queues up the next moves based on where the human player is
                    if (diffX > 0) {
                        for (int z = 0; z < diffX; z++) {
                            movement.push("move e");
                        }
                    } else {
                        for (int z = diffX; z < 0; z++) {
                            movement.push("move w");
                        }
                    }
                    if (diffY > 0) {
                        for (int z = 0; z < diffY; z++) {
                            movement.push("move s");
                        }
                    } else {
                        for (int z = diffY; z < 0; z++) {
                            movement.push("move n");
                        }
                    }
                }
            }
        }
        playerFound = false;
    }

    /**
     * Performs actions based on the outcome of a command
     *
     * @param result : The result of the command
     */
    @Override
    protected void handleResult(String result) {
        if (didLook) {
            calculateTurn(result);
            didLook = false;
        }
    }

    /**
     * Calculates the next action to take
     *
     * @return : The next action for the player that is worked out by the bot
     */
    @Override
    public String getNextAction() {
        //If there is a movement queued up already then make it
        if (!movement.isEmpty()) {
            return (String) movement.pop();
        } else {
            //If the bot has reached the human player's old location then use the look command
            if (playerFound) {
                didLook = true;
                playerFound = false;
                return "look";
            } else {
                //The bot does not know where the human player is so perform a random action
                switch (rand.nextInt(5)) {
                    case 0:
                        return "move n";
                    case 1:
                        return "move s";
                    case 2:
                        return "move e";
                    case 3:
                        return "move w";
                    case 4:
                        didLook = true;
                        return "look";
                }
            }
        }
        return null;
    }

}
