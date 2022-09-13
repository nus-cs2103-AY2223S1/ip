package byu;

import java.util.Scanner;

import exceptions.DukeException;

/**
 * A user interface that deals with the interaction with users.
 */
public class Ui {

    private static final String LOGO = "*\\(^o^)/*\n";
    private static final String WELCOME_MESSAGE = "Bonjour~~ I'm Byu! How can I help you?\n";
    private static final String ERROR_FORMAT = "Ohno! Error: %s\n";
    private static final String EXIT_MESSAGE = "Awww see you soon!!";

    private final Scanner sc = new Scanner(System.in);
    private String output;

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        assert Ui.LOGO != null : "logo should be initialized";
        return Ui.LOGO + Ui.WELCOME_MESSAGE;
    }

    /**
     * Returns the next line of user input.
     *
     * @return the string input by the user.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints the error message for an invalid input.
     */
    public String showError(DukeException e) {
        return String.format(Ui.ERROR_FORMAT, e.getMessage());
    }

    /**
     * Ends interactions with user and prints exit message.
     */
    public String exit() {
        sc.close();
        return Ui.EXIT_MESSAGE;
    }

    public void setOutput(String s) {
        this.output = s;
    }

    public String showOutput() {
        return this.output;
    }

}
