package jude;

import java.util.Scanner;

/**
 * Deals with the user interface of the chatbot.
 */
public class Ui {
    // Solution below adapted from
    // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
    private Scanner sc = new Scanner(System.in);

    /**
     * Creates a new UI instance.
     */
    public Ui() {
    }

    /**
     * Shows the welcome message of the chatbot, which should be done when the chatbot starts up.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Jude.");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a new empty line followed by the string "> ", indicating that the chatbot is ready
     * to receive a response from the user.
     */
    public void showCommandReadReady() {
        System.out.println();
        System.out.print("> ");
    }

    /**
     * Reads the command entered by the user and returns it.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
