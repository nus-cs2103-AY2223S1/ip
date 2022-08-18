package pnkp.duke;

import java.util.List;

/**
 * IO helper functions, enforcing a consistent format.
 */
public class IOFormat {
    /**
     * Output a line of text according to the format for bot messages.
     * Do not use \n for multiline text - use the list version.
     * @param text The text to output.
     */
    public static void say(String text) {
        say(List.of(text));
    }

    /**
     * Output some multiline text according to the format for bot messages.
     * @param lines A list of lines of text to output.
     */
    public static void say(List<String> lines) {
        for (int i=0; i < lines.size(); i++) {
            if (i==0) System.out.println("\uD83D\uDCAC " + lines.get(i));
            else      System.out.println("   " + lines.get(i));
        }
        System.out.println("─────");
    }
}
