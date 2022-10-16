package duke;

import java.util.Scanner;

/**
 * The User Interface (UI) that manages what is shown or printed to the user
 */
public class Ui {

    private static final String LINE = "\n========================================================";
    private static final String WELCOME_MESSAGE = "Hello, my name is Duke!\nHow can I help you today?";
    private static final String EXIT_MESSAGE = LINE + "\nGoodbye! Looking forward to see you again soon!" + LINE;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner input;

    /**
     * A constructor for the Ui class
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads the input from the user
     * @return The input given
     */
    public String readInput() {
        return input.nextLine();
    }

    /**
     * Prints out a welcome message upon running the program
     */
    public void printWelcomeMessage() {
        System.out.println(LOGO + LINE + "\n" + WELCOME_MESSAGE + LINE);
    }

    /**
     * Prints out an exit message upon ending the program
     */
    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints out an error message upon failing to read/load the tasks from a file
     *
     * @param fileName The path to the file
     */
    public void printLoadingError(String fileName) {
        System.out.println("Failed to find the task file: " + fileName + "\nCreating a new task file...");
    }

    public void printCorruptedError() {
        System.out.println("The data file is corrupted! (Eg. Illegal/unknown text)"
            + "\nResetting the task file...");
    }

    /**
     * Prints out a success message upon reading/loading the tasks from a file
     */
    public void printLoadingSuccessMessage() {
        System.out.println("Previously saved tasks have been loaded!");
    }

    /**
     * Prints out a divider line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out an error message
     * @param message The error message
     */
    public void printError(String message) {
        printLine();
        System.out.print(message);
        printLine();
    }
}
