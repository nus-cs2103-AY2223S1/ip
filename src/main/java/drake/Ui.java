package drake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Interact with the user.
 */
public class Ui {
    private static final String DASH = "------------------------------------------------------";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private final Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Format a line of text according to the format for the chat bubble on the GUI.
     * Do not use \n for multiline text - use the list version.
     *
     * @param text The text to output.
     */
    public List<String> chatBubbleText(String text) {
        return chatBubbleText(List.of(text));
    }

    /**
     * Format some multiline text according to the format for the chat bubble on the GUI.
     *
     * @param lines A list of lines of text to output.
     */
    public List<String> chatBubbleText(List<String> lines) {
        ArrayList<String> result = new ArrayList<>();
        for (String line : lines) {
            result.add("   " + line);
        }
        return result;
    }

    /**
     * Greets the user by printing the welcome message.
     */
    public List<String> replyWelcome() {
        ArrayList<String> reply = new ArrayList<>();
        reply.add("You used to call me on my cellphone");
        reply.add("!@#$%^&*()-+!@#$%^&*()`~`!@#$");
        reply.add("Drake's (me) the kind of guy to help you out uwu");
        reply.add("Go ahead, make that hotline bling");
        return reply;
    }

    /**
     * Reads input from the console into a String.
     *
     * @return The trimmed input line as a String.
     */
    public String readInput() {
        return sc.nextLine().trim();
    }

    /**
     * Prints the given line into the console.
     *
     * @param line The line to print.
     */
    public void printLine(Object line) {
        System.out.println(line);
    }

    /**
     * Prints the exit message.
     *
     */
    public String replyBye() {
        return "I'm down for you always. See you " + ANSI_RED + "<3" + ANSI_RESET;
    }

    /**
     * Prints a dash.
     */
    public void printDash() {
        System.out.println(DASH);
    }

    /**
     * Prints the given error message with special formatting.
     *
     * @param errorMessage The given error message.
     */
    public void printError(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }
}
