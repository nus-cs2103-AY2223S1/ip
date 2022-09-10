package chad;

/**
 * Contains general functions for use across all files
 */
public class Utility {
    /**
     * Formats response text from Chadbot and print
     * @param text text to be printed
     */
    public static String printText(String text) {
        String line = "_______________________________________________________";
        String output = line + "\n" + text + "\n" + line;
        return output;
    }

    /**
     * Prints text to console
     * @param text
     */
    public static void printToConsole(String text) {
        System.out.println(text);
    }
}
