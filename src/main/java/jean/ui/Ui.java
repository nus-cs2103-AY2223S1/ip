package jean.ui;

import java.util.Scanner;

/**
 * Handles user interaction and interface.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a new Ui object with a scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads command from user with its scanner object.
     *
     * @return User command with starting and ending spaces trimmed.
     */
    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /**
     * Prints Strings given in the parameter.
     *
     * @param s The message to be printed.
     */
    public void printMessage(String s) {
        System.out.println(s);
    }

    /**
     * Prints the errorMessage of a JeanException.
     *
     * @param errorMessage The message of the JeanException.
     */
    public void showJeanError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the errorMessage of all Exceptions in appropriate formatting.
     *
     * @param errorMessage The message of the exception.
     */
    public void showGeneralError(String errorMessage) {
        System.out.println("Something went wrong: " + errorMessage
                           + "\nQuelque chose a mal tourné: " + errorMessage);
    }

    public void greet() {
        System.out.println("Hello! I'm jean.Jean"
                           + "\nHow can I help you?"
                           + "\nBonjour! Je m'appelle jean.Jean"
                           + "\nVous désirez?");
    }

    public void end() {
        System.out.println("\tGoodbye! See you soon!"
                           + "\n\tAu revoir! À tout à l'heure!");
    }
}
