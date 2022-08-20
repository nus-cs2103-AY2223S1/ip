package duke.ui;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 *
 * @author Rama Aryasuta Pangestu
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner scanner;
    private final PrintWriter writer;

    /**
     * Constructs a helper tool to deal with interactions with the user.
     */
    public Ui() {
        scanner = new java.util.Scanner(System.in);
        writer = new PrintWriter(System.out);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        writer.println("Hello from\n" + Ui.LOGO);
        writer.println("What can I do for you?\n");
        writer.flush();
    }

    /**
     * Shows the divider line which precedes and follows all replies by Duke.
     */
    public void showLine() {
        this.showOutput("____________________________________________________________\n");
    }

    /**
     * Shows the error thrown by Duke.
     *
     * @param error the error message
     */
    public void showError(String error) {
        writer.println(error);
        writer.flush();
    }

    /**
     * Shows a reply by Duke to the user's input.
     *
     * @param output the reply message
     */
    public void showOutput(String output) {
        writer.print(output);
        writer.flush();
    }

    /**
     * Reads the user input.
     *
     * @return the user input
     */
    public String readCommand() {
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.trim().isBlank()) {
                return nextLine;
            }
        }
        return "";
    }
}
