package chad;

/**
 * Contains general functions for use across all files
 *
 */
public class Utility {
    /**
     * Formats response text from Chadbot and print
     *
     * @param text to be formatted
     */
    public static String printText(String text) {
        String line = "_______________________________________________________";
        return line + "\n" + text + "\n" + line;
    }

    /**
     * Prints text to console
     *
     * @param text to be printed to console
     */
    public static void printToConsole(String text) {
        System.out.println(text);
    }
}
