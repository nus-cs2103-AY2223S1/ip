package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Functions for interacting with the user.
 */
public class Ui {
    /**
     * Format a line of text according to the format for bot messages.
     * Do not use \n for multiline text - use the list version.
     *
     * @param text The text to output.
     */
    public static List<String> say(String text) {
        return say(List.of(text));
    }

    /**
     * Format some multiline text according to the format for bot messages.
     *
     * @param lines A list of lines of text to output.
     */
    public static List<String> say(List<String> lines) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                result.add("\uD83D\uDCAC " + lines.get(i));
            } else {
                result.add("   " + lines.get(i));
            }
        }
        return result;
    }

    private static final boolean DEV_MODE = false;

    /**
     * Format an error message.
     *
     * @param e The MessagefulException to output.
     */
    public static List<String> sayError(MessagefulException e) {
        if (DEV_MODE) {
            return sayAsError(e.toString());
        } else {
            return sayAsError(e.getHint());
        }
    }

    /**
     * Format an arbitrary string as if it is an error message.
     *
     * @param message The message to output.
     */
    public static List<String> sayAsError(String message) {
        return List.of("\uD83D\uDFE1 " + message);
    }

    public static void printLines(List<String> lines) {
        if (lines.isEmpty()) return;
        System.out.println(String.join("\n", lines) + "\n─────");
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
