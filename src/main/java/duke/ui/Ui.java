package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the ui used to interact with the user.
 */
public class Ui {

    private static final String DIVIDER = "────────────────────────────────────────────────────────────";
    private static final String PREFIX = "duke >> ";

    private static final String ANSI_GREEN = "\u001b[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static final String OUTPUT_COLOUR = ANSI_GREEN;

    private static final String MESSAGE_WELCOME = "Hello! I'm Duke.\nWhat can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private static final PrintStream out = System.out;
    private final Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String getUserCommand() {
        out.print("Enter command: ");
        String inputLine = in.nextLine();
        showWithColour(ANSI_RED + PREFIX + inputLine);

        return inputLine;
    }

    public void showWelcome() {
        showMessages(MESSAGE_WELCOME);
    }

    public void showExit() {
        showMessages(MESSAGE_EXIT);
    }

    /**
     * Shows the user the specified list of messages.
     */
    public void showMessages(String... messages) {
        showDivider();
        for (String m : messages) {
            showWithColour(m);
        }
        showDivider();
    }

    public void showDivider() {
        showWithColour(DIVIDER);
    }

    public void showWithColour(String message) {
        out.println(OUTPUT_COLOUR + message + ANSI_RESET);
    }
}
