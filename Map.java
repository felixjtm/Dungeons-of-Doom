import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

	// Representation of the map
	private char[][] map;

	// Map name
	private String mapName;

	// Gold required for the human player to win
	private int goldRequired;
	
	/**
	 * Default constructor, creates the default map "Very small Labyrinth of doom".
	 */
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][]{
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','E','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
		};
	}

	/**
	 * Constructor that accepts a map to read in from.
	 *
	 * @param fileName : The filename of the map file.
	 */
	public Map(String fileName) {
		readMap(fileName);
	}

    /**
     * @return : Gold required to exit the current map.
     */
    protected int getGoldRequired() {
        return goldRequired;
    }

	/**
	 * Sets a square on the map
	 *
	 * @param x : The x coordinate to be changed
	 * @param y : The y coordinate to be changed
	 * @param value :
	 */
	protected void setMapSquare(int x, int y, char value) {
    	map[y][x] = value;
	}

	/**
	 * Gets a square on the map
	 *
	 * @param x : The x coordinate to be checked
	 * @param y : The y coordinate to be checked
	 * @return : The char on the map at the specified coordinates
	 */
	protected char getMapSquare(int x, int y) {
    	return map[y][x];
	}

	/**
	 * @return : The length of a row in the map
	 */
	protected int getRowLength() {
    	return map[0].length;
	}

	/**
	 * @return : The height of the map
	 */
	protected int getHeight() {
    	return map.length;
	}

    /**
     * Reads the map from file.
     *
     * @param : Name of the map's file.
     */
    protected void readMap(String fileName) {
		String line;

		/*
		* Gets the line count in order to iterate through the whole file,
		* and specify the size of the array
		*/
		int lineCount = getLineCount(fileName);

		//Defines the array for the map using the number of lines, subtracting 2 due to the map name and gold required
		map = new char[lineCount - 2][];
    	try {
    		//Attempts to read a file at the specified location
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			//The first line is the map name, so separates the name out of the line and stores it
			line = reader.readLine();
			mapName = line.substring(5);

			//The second line is the gold required, so separates the number out of the line and stores it
			line = reader.readLine();
			goldRequired = Integer.parseInt(line.substring(4));


			//Iterates through the rest of the file and stores each line in a char array
			for (int i = 0; i <= lineCount - 3; i++) {
				line = reader.readLine();
				map[i] = line.toCharArray();
			}
			reader.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
		} catch(Exception e) {
    		System.out.println("File in wrong format.");
		}
    }

	/**
	 * Counts the number of lines in a file
	 *
	 * @param fileName : The file to be read from
	 * @return : The number of lines in the file specified
	 */
	private int getLineCount(String fileName) {
		int lines = 0;
    	try {
    		//Attempts to read the file
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			//Increments a counter for each line in the file
			while (reader.readLine() != null) {
				lines++;
			}
			reader.close();
		} catch (Exception e) {
    		//If the file does not exist, return 0
    		return 0;
		}
    	return lines;
	}


}
