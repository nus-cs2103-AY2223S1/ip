package duke.ui;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.PrintStream;
import java.util.Scanner;

import duke.commands.CommandResult;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

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

    public String getUserCommand() {
        out.print("Enter command: ");
        String inputLine = in.nextLine();
        showWithColour("MAGENTA", PREFIX + inputLine);

        return inputLine;
    }

    public void showResultToUser(CommandResult result) {
        showDivider();
        showWithCurrentColour(result.getFeedbackToUser());
        showDivider();
    }

    public void showWithCurrentColour(String message) {
        out.println(ansi().fg(currentColour).a(message).reset());
    }

    public void showWithColour(String inputColour, String message) {
        out.println(ansi().fg(Ansi.Color.valueOf(inputColour.toUpperCase())).a(message).reset());
    }

    public void unloadJansi() {
        AnsiConsole.systemUninstall();
    }

    public void setOutputColor(String inputColour) {
        currentColour = Ansi.Color.valueOf(inputColour);
    }

    public void showWelcome() {
        showDivider();
        showWithCurrentColour(MESSAGE_WELCOME);
        showDivider();
    }

    public void showExit() {
        showDivider();
        showWithCurrentColour(MESSAGE_EXIT);
        showDivider();
    }

    private void showDivider() {
        showWithCurrentColour(DIVIDER);
    }


}
