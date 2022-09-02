package jarvis.ui;

import java.util.Scanner;

/**
 * Ui --- prints response based on user input.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Print feedback message.
     *
     * @param message feedback message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Prints greeting message.
     */
    public String greet() {
        return "Hello! I am Jarvis! What can I do for you?";
    }
}
