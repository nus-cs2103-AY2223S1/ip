package byu;

import java.util.Scanner;

import exceptions.DukeException;

/**
 * Represents a user interface that deals with the interaction with users.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);
    private final String logo = "*\\(^o^)/*";
    private String output;

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println(logo);
        System.out.println("Bonjour~~ I'm Byu! How can I help you?");
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
        return String.format("Ohno! Error: " + e.getMessage() + "\n");
    }

    /**
     * Ends interactions with user and prints exit message.
     */
    public void exit() {
        System.out.print("Awww see you soon!!");
        sc.close();
    }

    public void setOutput(String s) {
        this.output = s;
    }

    public String showOutput() {
        return this.output;
    }
}
