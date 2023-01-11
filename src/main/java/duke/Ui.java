package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Deals with the user interface of duke.
 */
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
     * Creates a new Ui instance.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Creates a new Ui instance.
     *
     * @param in The input reader.
     * @param out The output displayer.
     */
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
     * Shows the error message.
     *
     * @param e DukeException type exception.
     */
    public String showError (DukeException e) {
        return e.toString();
    }

}
