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
     * Returns the welcome message of the chatbot, which should be done when the chatbot starts up.
     *
     * @return The welcome message of the chatbot.
     */
    public String showWelcome() {
        return "Hello! I'm Jude, named after the Beatles' hit song 'Hey Jude'.\n"
            + "What can I do for you?";
    }
}
