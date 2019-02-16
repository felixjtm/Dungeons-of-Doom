import java.io.File;
import java.util.Scanner;

/**
 * Lists all files in current directory and allows the user to select one
 *
 */
public class FileGetter {

    //Used to read the file
    private Scanner reader;

    //Stores the file path the user
    private String fileName = null;

    /**
     * @return : The name of the file the user has selected
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Allows the user to select a file from a list of files
     *
     * @return : Whether or not the user has selected a file and if that file exists
     */
    public boolean getFileInput() {
        //Stores the file that the user selects
        File finalFile;

        //Initialises the Scanner in order to allow user input
        reader = new Scanner(System.in);

        //Stores the string the user inputs when asked to select a file
        String selection;

        System.out.println("Enter map name or leave blank to use default.");
        System.out.println("Available files: \n");

        //Lists all .txt files in the current directory
        listFiles();

        System.out.print("\nMap choice: ");

        //Reads the user input, converts it to lower case and removes leading and trailing spaces
        selection = reader.nextLine().toLowerCase().trim();

        //If the name the user entered didn't include the file type .txt then add it manually
        if (selection.endsWith(".txt")) {
            fileName = selection;
        } else {
            fileName = selection + ".txt";
        }

        //Creates a File with the given file path
        finalFile = new File(fileName);

        //If the file exists then return true, otherwise return false
        if (finalFile.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Lists all the files in the current directory with file type .txt, except the ReadMe
     *
     */
    private void listFiles() {

        //File array to store the paths of the different files
        File[] paths;

        //Gets the files in the current directory
        File currentFiles = new File(".");

        //Assigns all file paths to the paths[] array
        paths = currentFiles.listFiles();

        /*
         * Iterates through every file path and checks if it a txt file that is not the ReadMe and displays it,
         * does not display the "./" at the front of the path
         */
        for (File path: paths) {
            if (path.toString().endsWith(".txt") && path.toString().substring(2) != ("ReadMe.txt")) {
                System.out.println(path.toString().substring(2));
            }
        }
    }
}
