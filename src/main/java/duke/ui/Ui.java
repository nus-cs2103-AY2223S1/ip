package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * Abstraction for user interactions, used to print out responses
 * from user commands.
 */
public class Ui {
    private static final String DIVIDER =
            "\n-----------------------------------------------";
    private final Scanner s;

    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Shows intro message to user.
     */
    public void showIntro() {
        String intro = "Welcome to Apollo!\n"
                + "How can I help you today?";
        System.out.println(intro + DIVIDER);
    }

    /**
     * Shows error message to user.
     *
     * @param e error encountered
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage() + DIVIDER);
    }

    /**
     * Shows outro message to user.
     */
    public void showOutro() {
        System.out.println("Goodbye, see you soon!" + DIVIDER);
    }

    /**
     * Shows input string to user as a message.
     *
     * @param s input string to be shown to user
     */
    public void showToUser(String s) {
        System.out.println(s + DIVIDER);
    }

    /**
     * Accepts user input and returns it as a string.
     *
     * @return user input as a string
     */
    public String getUserInput() {
        return s.nextLine();
    }
}
