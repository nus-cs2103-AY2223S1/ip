package duke;

import java.util.List;
import java.util.Scanner;

/**
 * Functions for interacting with the user.
 */
public class Ui {
    /**
     * Output a line of text according to the format for bot messages.
     * Do not use \n for multiline text - use the list version.
     *
     * @param text The text to output.
     */
    public static void say(String text) {
        say(List.of(text));
    }

    /**
     * Output some multiline text according to the format for bot messages.
     *
     * @param lines A list of lines of text to output.
     */
    public static void say(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                System.out.println("\uD83D\uDCAC " + lines.get(i));
            } else {
                System.out.println("   " + lines.get(i));
            }
        }
        System.out.println("─────");
    }

    private static final boolean DEV_MODE = false;

    /**
     * Output an error message.
     *
     * @param e The MessagefulException to output.
     */
    public static void sayError(MessagefulException e) {
        if (DEV_MODE) {
            sayAsError(e.toString());
        } else {
            sayAsError(e.message());
        }
    }

    /**
     * Output an arbitrary string as if it is an error message.
     *
     * @param message The message to output.
     */
    public static void sayAsError(String message) {
        System.out.println("\uD83D\uDFE1 " + message);
        System.out.println("─────");
    }

    private static final Scanner stdin = new Scanner(System.in);

    /**
     * Reads a line of text from standard input.
     *
     * @return The read line of text.
     */
    public static String readLine() {
        return stdin.nextLine();
    }
}
