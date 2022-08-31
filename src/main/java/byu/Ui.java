package byu;

import exceptions.DukeException;

import java.util.Scanner;

/**
 * Represents a user interface that deals with the interaction with users.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);
    private final String logo = "*\\(^o^)/*";

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
    public void showError(DukeException e) {
        System.out.print("Ohno! Error: " + e.getMessage() + "\n");
    }

    /**
     * Ends interactions with user and prints exit message.
     */
    public void exit() {
        System.out.print("Awww see you soon!!");
        sc.close();
    }

}
