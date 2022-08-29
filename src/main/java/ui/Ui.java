package ui;

import java.util.Scanner;

/**
 * Ui has all the methods and logic that interacts directly with the user through
 * the console.
 */
public class Ui {

    protected Scanner scanner;
    protected String input;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome message upon starting Fred chat bot.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm fred.Fred! What can I do for you?");
    }

    /**
     * Shows line that separates chat bot messages and user input.
     */
    public void showLine() {
        System.out.println("___________________________________________________");
    }

    /**
     * Show loading error from start of chat bot.
     */
    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    /**
     * Read command from user and passes it to the rest of the program.
     * @return String containing command from user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows custom message to user.
     * @param message
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Show exit message when exiting chat bot.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Show error message when an error is detected in the program.
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

}
