package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * The interface responsible for handling the app's interaction with the user.
 */
public class TextUi {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String PREFIX = "duke >> ";

    private static final PrintStream out = System.out;
    private final Scanner in = new Scanner(System.in);

    /**
     * Gets the command inputted by the user and returns it.
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        String inputLine = in.nextLine();
        out.println(PREFIX + inputLine);

        return inputLine;
    }

    /**
     * Welcomes the user.
     */
    public void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Bids the user goodbye. This should only be shown on the ExitCommand.
     */
    public void showGoodByeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Displays a message to the user.
     * @param message the message to be displayed
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
