package duke;

/**
 * Ui is a class that deals with handling console outputs.
 */
public class Ui {
    final static String DIVIDER_LINE = "\t-------------------------------------------";

    /**
     * Prints a welcome message to the console.
     */
    public static void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm Du-Kopi.\n\tWhat can I do for you?");
        showLine();
    }

    /**
     * Prints a goodbye message to the console.
     */
    public static void showGoodbye() {
        showLine();
        System.out.println("\tBye. Hope to teh-See you again soon!");
        showLine();
    }

    /**
     * Prints a line to the console.
     */
    public static void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints the provided string to the console.
     *
     * @param line The line to be printed to the console.
     */
    public static void show(String line) {
        System.out.println(line);
    }

}
