/**
 * Base class for player classes, gives methods that every player uses
 *
 */
public class Player {
    //Stores the coordinates for the player
    private int coordinates[] = {0,0};

    //Stores the character that displayed on the map for the player
    protected char playerChar;

    /**
     * @return : The x coordinate for the player
     */
    protected int getX() {
        return coordinates[0];
    }

    /**
     * @return : The y coordinate for the player
     */
    protected int getY() {
        return coordinates[1];
    }

    /**
     * @return : The character that is displayed on the map for the player
     */
    public char getPlayerChar() {
        return playerChar;
    }

    /**
     * Method that is overridden by subclasses, used to get input for the next action
     *
     * @return : null
     */
    protected String getNextAction() {
        return null;
    }

    /**
     * Method that is overridden by subclasses, used to handle the result of an action
     *
     * @param result : The result of the action
     */
    protected void handleResult(String result) {}

    /**
     * Sets the coordinates for the player
     *
     * @param x : The x coordinate of the player
     * @param y : The y coordinate of the player
     */
    protected void setCoords(int x, int y) {
        coordinates[0] = x;
        coordinates[1] = y;
    }

}
