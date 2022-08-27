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
    private final String divider = "------------------------";

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
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(divider);
    }

    /**
     * Reads the command line input.
     * @return
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Shows the divider.
     */
    public void showLine() {
        System.out.println(divider);
    }

    /**
     * Shows the error message.
     * @param e DukeException type exception.
     */
    public void showError (DukeException e) {
        System.out.println(e);
    }

}
