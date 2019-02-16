import java.util.Random;
/**
 * Contains the main logic part of the game, as it processes
 *
 */
public class GameLogic {

    //Declares the map variable for the game
	private Map map;

	//Declares the human player variable for the game
	private HumanPlayer player;

	//Declares the bot player variable for the game
	private BotPlayer bot;

	//Stores whether or not the game has ended
	private boolean inProgress;

	//Declares and instantiates the variable for generating random numbers
	private Random randomNum = new Random();

	//Declares the variable that stores the turn number
	private int turn;


    /**
     * Constructor that initialises variables
     */
	public GameLogic() {
        player = new HumanPlayer();
        bot = new BotPlayer();
        inProgress = true;
        turn = 1;
    }

    /**
     * Creates the default map and randomly places the human player and bot onto it
     */
    protected void generateMap() {
	    map = new Map();
        randomisePlayerLocation(player);
        randomisePlayerLocation(bot);
    }

    /**
     * Creates the map from a file path and randomly places the human player and bot onto it
     */
    protected void generateMap(String fileName) {
        map = new Map(fileName);
        randomisePlayerLocation(player);
        randomisePlayerLocation(bot);
    }

    /**
	 * Checks if the game is running
	 *
     * @return if the game is running
     */
    protected boolean gameRunning() {
        //If the bot and human player are on the same square then end the game
        if (player.getX() == bot.getX() && player.getY() == bot.getY()) {
            inProgress = false;
        }
        return inProgress;
    }

    /**
	 * Returns the gold required to win
	 *
     * @return : Gold required to win
     */
    protected int hello() {
        return map.getGoldRequired();
    }
	
	/**
	 * Returns the gold currently owned by the player
	 *
     * @return : Gold currently owned
     */
    protected int gold() {
        return player.getGold();
    }

    /**
     * Checks if movement is legal and updates player's coordinates
     *
     * @param thisPlayer : The player making the movement
     * @param direction : The direction of the movement
     * @return : If success or not
     */
    protected String move(Player thisPlayer, String direction) {
        //Initialises the variables that will store the values for the target coordinates
        int tarX = thisPlayer.getX();
        int tarY = thisPlayer.getY();

        //Changes the target coordinates based on the movement made
        switch (direction) {
            case "n":
                tarY--;
                break;
            case "s":
                tarY++;
                break;
            case "w":
                tarX--;
                break;
            case "e":
                tarX++;
                break;
        }
        //If the target coordinates are a legal move then make the move
        if (map.getMapSquare(tarX, tarY) != '#') {
            thisPlayer.setCoords(tarX, tarY);
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    /**
     * Converts the map from a 2D char array to a single string.
     *
     * @return : A String representation of the game map.
     */
    protected String look(Player thisPlayer) {
        //Gets the player's coordinates so that the grid can be around the player
        int x = thisPlayer.getX();
        int y = thisPlayer.getY();

        //Stores the output that is returned
        String output = "";

        //Iterates through a 5x5 grid around the player
        for (int j = y - 2; j <= y + 2; j++) {
            for (int i = x - 2; i <= x + 2; i++) {
                //If the human or bot player is in the square then output their player character instead
                if (i == player.getX() && j == player.getY()) {
                    output += player.getPlayerChar();
                } else if (i == bot.getX() && j == bot.getY()) {
                    output += bot.getPlayerChar();
                } else if (i > -1 && i < map.getRowLength() && j > -1 && j < map.getHeight()) {
                    //If the space is inside of the map boundary then add the char to the output
                    output += (map.getMapSquare(i,j));
                } else {
                    //If the space is outside of the map boundary then add a space to the output
                    output += (' ');
                }
            }
            //Adds a new line to the output since it is the next row in the 5x5 grid
            output += "\n";
        }
        return output;
    }

    /**
     * Processes the player's pickup command, updating the map and the player's gold amount
     *
     * @return If the player successfully picked-up gold or not and how much gold is now owned by the player
     */
    protected String pickup() {
        //If the player is on a square that contains gold then pick it up
        if (map.getMapSquare(player.getX(), player.getY()) == 'G') {
            player.incrementGold();
            map.setMapSquare(player.getX(), player.getY(), '.');
            return "SUCCESS. Gold owned: " + player.getGold();
        } else {
            return "FAIL. Gold owned: " + player.getGold();
        }
    }

    /**
     * Quits the game, shutting down the application.
     */
    protected void quitGame() {
        //If the player has won the game
        if (map.getMapSquare(player.getX(), player.getY()) == 'E' && gold() == hello()) {
            System.out.println("WIN! You are able to escape with the gold.");
        } else if(player.getX() == bot.getX() && player.getY() == bot.getY()) {
            //If the player has been caught by the bot
            System.out.println("LOSE! You were caught by the bot. Game over.");
        } else {
            //If the player attempted to leave but was not on the exit or had enough gold
            System.out.println("LOSE! You were unable to reach the end of the dungeon. Game over.");
        }
        System.out.println("Press enter to quit...");

        //Reads the input when the player presses enter, then exits the game
        try{System.in.read();}
        catch(Exception e){}
        System.exit(0);
    }

    /**
     * Gets a random location for the player that is not on gold or a wall
     *
     * @param thisPlayer : The player that is to be placed on the map
     */
    protected void randomisePlayerLocation(Player thisPlayer) {
        //Stores the randomised x and y values
        int randX;
        int randY;

        //Stores the boundaries of the map
        int sizeX = map.getRowLength();
        int sizeY = map.getHeight();

        //Generates random coordinates until they are on a space that does not contain gold or a wall
        do {
            randX = randomNum.nextInt(sizeX - 1);
            randY = randomNum.nextInt(sizeY - 1);
        } while(map.getMapSquare(randX, randY) != '.' && map.getMapSquare(randX, randY) != 'E');
        //Sets the coordinates for the player
        thisPlayer.setCoords(randX, randY);
    }

    /**
     * Increments the turn counter
     */
    protected void incrementTurn() {
        turn++;
    }

    /**
     * @return : The turn number
     */
    protected int getTurn() {
        return turn;
    }

    /**
     * Does actions based on the input received from a player
     *
     * @param thisPlayer : The player that has an input
     */
    private void HandleInput(Player thisPlayer) {
        //Gets the input from the player, converts it to lower case and separates the commands by spaces
        String input[] = (thisPlayer.getNextAction().toLowerCase()).split(" ");

        //Performs different actions based on the first word of the command
        switch (input[0]) {
            case "hello":
                //Gets the gold the player needs to win
                thisPlayer.handleResult(Integer.toString(hello()));
                break;
            case "gold":
                //Gets the gold the player has
                thisPlayer.handleResult(Integer.toString(gold()));
                break;
            case "move":
                //Gets the direction to move based on the second word of the command
                if (input.length > 1) {
                    String direction = input[1];

                    //If it is a valid direction
                    if (direction.equals("n") || direction.equals("s") || direction.equals("e") || direction.equals("w")) {
                        thisPlayer.handleResult(move(thisPlayer, direction));
                    } else {
                        thisPlayer.handleResult("Invalid direction");
                    }
                } else {
                    thisPlayer.handleResult("Invalid direction");
                }
                break;
            case "look":
                //Gets a 5x5 grid around the player
                thisPlayer.handleResult(look(thisPlayer));
                break;
            case "pickup":
                //Attempts to pickup gold from the tile the player is on
                thisPlayer.handleResult(pickup());
                break;
            case "quit":
                //Quits the game
                quitGame();
                break;
            default:
                //If the command is not recognised, inform the user
                thisPlayer.handleResult("Invalid command");
                break;
        }
    }

    /**
     * Main method that runs the game
     *
     * @param args : Any arguments that are passed when the code is run
     */
	public static void main(String[] args) {

	    //Declares variable for game logic so that the game can run
        GameLogic logic = new GameLogic();

        //Declares variable for getting the map file
        FileGetter file = new FileGetter();

        //Gets the user to enter a file path
        if (file.getFileInput()) {
            //If the path is valid then play with the specified map
            logic.generateMap(file.getFileName());
        } else {
            //If the path is not valid then use the default map
            System.out.println("Using default map");
            logic.generateMap();
        }

        //While the game has not finished
		while(logic.gameRunning()) {
		    //Outputs the turn number
		    System.out.println("Turn: " + logic.getTurn());

		    //Human player's turn
		    logic.HandleInput(logic.player);

		    //Bot player's turn
            logic.HandleInput(logic.bot);

            //Increment turn count
            logic.incrementTurn();
        }

        //Exits the game
        logic.quitGame();
    }
}