package duke.ui;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.PrintStream;
import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import duke.commands.CommandResult;

/**
 * Represents the ui used to interact with the user.
 */
public class TextUi {

    private static final String DIVIDER = "────────────────────────────────────────────────────────────";
    private static final String PREFIX = "duke >> ";

    private static final String MESSAGE_WELCOME = "Hello! I'm Duke.\nWhat can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private static final PrintStream out = System.out;
    private final Scanner in;
    private Ansi.Color currentColour = Ansi.Color.CYAN;

    /**
     * Sets up the Ui, loads the Jansi library (enables colour support on Windows/Unix)
     */
    public TextUi() {
        in = new Scanner(System.in);
        AnsiConsole.systemInstall();
    }

    /**
     * Gets the command inputted by the user and returns it.
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        String inputLine = in.nextLine();
        out.println(ansi().fg(Ansi.Color.MAGENTA).a(PREFIX + inputLine).reset());

        return inputLine;
    }

    /**
     * Shows the result of the command to the user.
     */
    public void showResultToUser(CommandResult result) {
        showDivider();
        showWithCurrentColour(result.getFeedbackToUser());
        showDivider();
    }

    /**
     * Shows messages to the user with the set output colour.
     */
    public void showWithCurrentColour(String... messages) {
        for (String s : messages) {
            out.println(ansi().fg(currentColour).a(s).reset());
        }
    }

    public void unloadJansi() {
        AnsiConsole.systemUninstall();
    }

    public void setOutputColor(String inputColour) {
        currentColour = Ansi.Color.valueOf(inputColour);
    }

    /**
     * Shows the welcome message when Duke starts.
     */
    public void showWelcome() {
        showDivider();
        showWithCurrentColour(MESSAGE_WELCOME);
        showDivider();
    }

    /**
     * Shows the exit message when user inputs the exit command.
     */
    public void showExit() {
        showDivider();
        showWithCurrentColour(MESSAGE_EXIT);
        showDivider();
    }

    private void showDivider() {
        showWithCurrentColour(DIVIDER);
    }


}
