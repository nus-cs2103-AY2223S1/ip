package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Duke program.
 */
public class Duke {
    private static boolean isRunning;
    private static StringBuilder response = new StringBuilder();

    /**
     * Starts the Duke program and performs the necessary start up operations.
     * This includes displaying the greeting response and loading the task list from file.
     */
    public static void initialize() {
        try {
            Storage.loadData();
        } catch (IOException e) {
            System.out.println("Error: Failed to access data");

            addToResponse("Error: Failed to access data");
        }

        Ui.displayStartUpText();

        isRunning = true;
    }

    /**
     * Stops the Duke program and performs the necessary exit operations.
     * This includes displaying the exit response and saving the task list to file.
     */
    public static void exit() {
        try {
            Storage.saveData();
        } catch (IOException e) {
            System.out.println("Error: Failed to access data");

            addToResponse("Error: Failed to access data");
        }

        Ui.displayExitText();

        isRunning = false;
    }

    /**
     * Gives Duke an input string to process using Parser.
     * Parser will generate a command based on this input, and perform operations.
     * Ui then creates a response to the user and passes it to Duke via addToResponse.
     *
     * @param input The input string to be processed by Parser.
     */
    public static void giveInput(String input) {
        Ui.generateLine();
        try {
            Parser.parseInput(input);
        } catch (DukeException e) {
            System.out.println(e.getMessage());

            addToResponse(e.getMessage() + "\n");
        }
        Ui.generateLine();
    }

    public static void addToResponse(String output) {
        response.append(output);
    }

    /**
     * Gets a string that is a response to the user's last command.
     *
     * @return A string that is a response to the user's last command.
     */
    public static String getResponse() {
        return response.toString();
    }

    /**
     * Clears the response to be displayed to the user when getResponse is called.
     */
    public static void clearResponse() {
        response = new StringBuilder();
    }

    /**
     * Gets whether Duke is still running.
     *
     * @return True if Duke is running, otherwise false.
     */
    public static boolean getIsRunning() {
        return isRunning;
    }

    /**
     * The method used to run the Duke program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        initialize();

        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            Ui.generateLine();
            String input = scanner.nextLine();
            Ui.generateLine();

            try {
                Parser.parseInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
