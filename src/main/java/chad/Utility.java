package chad;

/**
 * Contains general functions for use across all files
 */
public class Utility {
    /**
     * Formats response text from Chadbot and print
     * @param text text to be printed
     */
    public static void printText(String text) {
        String line = "____________________________________________________________";
        String output = line + "\n" + text + "\n" + line;
        System.out.println(output);
    }
}
