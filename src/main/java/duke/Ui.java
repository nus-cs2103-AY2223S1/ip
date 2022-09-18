package duke;
import java.util.Scanner;

/**
 * Handles the user input.
 */
public class Ui {

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    /**
     * Creates an instance of the user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "\nHow can I help you?");
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        System.out.println("Many thanks from \n" + logo + "\nHave a nice day!");
    }

    /**
     * Reads the user input.
     * @return the string of the user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a message as given by the input argument.
     * @param message the string to be printed
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the loading error when initialised filePath cannot be found.
     */
    public void showLoadingError() {
        System.out.println("File not found, initialising new task list...");
    }

    /**
     * Prints an error message as given by the input argument.
     * @param errorMessage the string to be printed
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
