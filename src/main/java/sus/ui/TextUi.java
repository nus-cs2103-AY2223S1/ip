package sus.ui;

import java.io.PrintStream;
import java.util.Scanner;

import sus.commands.CommandResult;
import sus.common.Messages;

/**
 * Represents the ui used to interact with the user.
 */
public class TextUi {

    private static final String DIVIDER = "────────────────────────────────────────────────────────────";
    private static final String PREFIX = "sus >> ";

    private static final PrintStream out = System.out;
    private final Scanner in;
    /**
     * Sets up the Ui, loads the Jansi library (enables colour support on Windows/Unix)
     */
    public TextUi() {
        in = new Scanner(System.in);
    }

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
     * Shows the result of the command to the user in the GUI.
     */
    public void showResultToUser(CommandResult result) {
        showDivider();
        showMessages(result.getCommandResult());
        showDivider();
    }

    /**
     * Shows messages to the user with the set output colour.
     */
    public void showMessages(String... messages) {
        for (String s : messages) {
            out.println(s);
        }
    }
    /**
     * Shows the welcome message when Duke starts.
     */
    public void showWelcome() {
        showDivider();
        showMessages(Messages.MESSAGE_WELCOME);
        showDivider();
    }

    /**
     * Shows the exit message when user inputs the exit command.
     */
    public void showExit() {
        showDivider();
        showMessages(Messages.MESSAGE_EXIT);
        showDivider();
    }

    private void showDivider() {
        showMessages(DIVIDER);
    }

}
