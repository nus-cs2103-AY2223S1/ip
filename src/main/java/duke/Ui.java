package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    /**
     * Input scanner.
     */
    private final Scanner in;
    /**
     * Output stream.
     */
    private final PrintStream out;
    /**
     * Divider to print.
     */
    private final String DIVIDER = "------------------------";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Shows the welcome message for the bot.
     */
    public String showWelcomeMessage() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Hello! I'm Duke\n");
        toReturn.append("What can I do for you?\n");
        return toReturn.toString();
    }

    /**
     * Reads the command line input.
     *
     * @return
     */

    /**
     * Shows the divider.
     */
    public String showLine() {
        return DIVIDER + "\n";
    }

    /**
     * Shows the error message.
     *
     * @param e DukeException type exception.
     */
    public String showError (DukeException e) {
        return e.toString();
    }

}
