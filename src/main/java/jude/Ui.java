package jude;

import java.util.Scanner;

/**
 * Deals with the user interface of the chatbot.
 */
public class Ui {
    private static final String CREDITS = "Credits:\n"
            + "Jude the chatbot's profile picture is taken from "
            + "https://www.iconfinder.com/search?q=person&price=free (original author Bombasticon "
            + "Studio)"
            + "under Free for commercial use licence.\n"
            + "User profile picture is taken from https://www.pexels"
            + ".com/photo/man-smiling-behind-wall-220453/ "
            + "licenced under CC0. Original author Pixabay.";
    private static final String WELCOME_MESSAGE = "Hello! I'm Jude, named after the Beatles' hit "
            + "song 'Hey Jude'.\nWhat can I do for you?";

    // Solution below adapted from
    // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
    private final Scanner sc = new Scanner(System.in);

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
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns credits message for third-party images used in this project.
     *
     * @return Credits message for third-party images used in this project.
     */
    public String getCredits() {
        return CREDITS;
    }

    /**
     * Prints a new empty line followed by the string "> ", indicating that the chatbot is ready
     * to receive a response from the user. This method is for use in console mode only.
     */
    void showCommandReadReady() {
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

    /**
     * Displays the reply when bye command is executed. This method should only be called on
     * console mode.
     */
    public void showByeMessage() {
        System.out.println("Goodbye! Have a nice day!");
    }
}
