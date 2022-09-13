package duke.ui;

import java.util.Scanner;

/**
 * Handles outputs to the UI.
 *
 * @author Kang Wei
 */
public class Ui {

    /**
     * Prints a line break to the ui to signify the end of a message.
     */
    private static void endMessage() {
        System.out.println("-".repeat(100));
    }

    /**
     * Prints a message to the ui. Also prints a new line.
     *
     * @param message The message to be printed.
     */
    public static void print(String message) {
        System.out.println(message);
        endMessage();
    }

    /**
     * Sets the ui to await the user's input.
     *
     * @param sc The Scanner object with which to read the user's input.
     * @return Returns the user's input.
     */
    public static String awaitUserInput(Scanner sc) {
        System.out.print("You: ");
        String input = sc.nextLine();
        return input;
    }
}
