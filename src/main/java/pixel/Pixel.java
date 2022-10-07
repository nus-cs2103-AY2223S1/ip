package pixel;

import java.util.Scanner;

import pixel.util.Parser;

/**
 * Main class of chatbot
 */
public class Pixel {

    private static int taskCount = 0;
    private final Scanner myScanner = new Scanner(System.in); // Create a Scanner object
    private final Parser parser;

    /**
     * Constructor for a new Pixel object
     *
     * @param filePath path of file to save the tasks
     */
    public Pixel(String filePath) {
        this.parser = new Parser(filePath);
    }

    /**
     * Resets the count for the total number of tasks recorded
     *
     * @param newCount new count of tasks to reset to
     */
    public static void resetTaskCount(int newCount) {
        taskCount = newCount;
    }

    /**
     * Retrieves the count for the total number of tasks recorded
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Increases the count for the total number of tasks recorded by one
     */
    public static void addOneToTaskCount() {
        taskCount += 1;
    }

    /**
     * Decreases the count for the total number of tasks recorded by one
     */
    public static void minusOneToTaskCount() {
        taskCount -= 1;
    }

    /**
     * Gets the parser object to parse the raw user input
     *
     * @param userInput the raw input of the user
     * @return the response from Pixel
     */
    public String parserParse(String userInput) {
        return this.parser.parse(userInput);
    }

    // obsolete since the implementation of GUI
    /**
     * Obsolete method to run Pixel.
     * Previously used for CLI.
     */
    public void run() {
        while (myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine(); // Read user input
            parser.parse(userInput);
            run();
        }
    }
}

