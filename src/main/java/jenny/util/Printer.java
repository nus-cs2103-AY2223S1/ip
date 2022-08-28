package jenny.util;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Prints to the provided {@link PrintStream}.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public final class Printer {
    private final PrintStream out;
    private static final String UNDERSCORE = "_";
    private static final int WINDOW_LENGTH = 60;

    /**
     * Creates a new instance of a printer using the provided {@link PrintStream}.
     *
     * @param out a {@link PrintStream}.
     */
    public Printer(PrintStream out) {
        this.out = out;
    }

    /**
     * Sends a series of underscores to the output stream.
     */
    public void printLine() {
        out.println("\t" + UNDERSCORE.repeat(WINDOW_LENGTH));
    }

    /**
     * Sends a series of messages to the output stream.
     */
    public void print(String[] messages) {
        printLine();
        for (String message : messages) {
            out.println("\t" + message);
        }
        printLine();
    }

    /**
     * Sends a series of messages to the output stream.
     */
    public void print(ArrayList<String> messages) {
        printLine();
        for (String message : messages) {
            out.println("\t" + message);
        }
        printLine();
    }
}
