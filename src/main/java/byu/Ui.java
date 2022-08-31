package byu;

import java.util.Scanner;

/**
 * Represents a user interface that deals with the interactions with users
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = "*\\(^o^)/*";
        System.out.println(logo);
        System.out.println("Bonjour~~ I'm Byu! How can I help you?");
    }

    /**
     * Returns the next line of the user input.
     *
     * @return the string input by the user.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showError(String message) {
        System.out.print("Ohno! " + message);
    }

    /**
     * Ends interactions with user and prints exit message.
     */
    public void exit() {
        System.out.print("Awww see you soon!!");
        sc.close();
    }

}
