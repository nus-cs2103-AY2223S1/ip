package jenny.util;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Prints stuff to the console.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public final class Printer {
    private static final PrintStream PRINT_STREAM = System.out;
    private static final String UNDERSCORE = "_";
    private static final int WINDOW_LENGTH = 60;

    public static void printLine() {
        PRINT_STREAM.println("\t" + UNDERSCORE.repeat(WINDOW_LENGTH));
    }

    public static void print(String[] messages) {
        printLine();
        for (String message : messages) {
            PRINT_STREAM.println("\t" + message);
        }
        printLine();
    }

    public static void print(ArrayList<String> messages) {
        printLine();
        for (String message : messages) {
            PRINT_STREAM.println("\t" + message);
        }
        printLine();
    }
}
