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
    public void printMessage(String message) {
        System.out.println(">> " + message);
    }

    /**
     * Prints greeting message.
     */
    public void greet() {
        System.out.println(">> Hello! I am Jarvis! What can I do for you?");
    }
}
