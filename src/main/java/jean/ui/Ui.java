package jean.ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import jean.exception.JeanException;

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
    public String readCommand() throws JeanException {
        String command = this.sc.nextLine().trim();
        if (command.isEmpty()) {
            throw new JeanException("Command should not be empty!");
        }
        return command;
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
     * @param e The instance of JeanException.
     */
    public String getJeanError(JeanException e) {
        return (e.getMessage());
    }

    /**
     * Prints the errorMessage of all Exceptions in appropriate formatting.
     *
     * @param e The exception.
     */
    public String getGeneralError(Exception e) {
        String message = "";
        if (e instanceof DateTimeParseException) {
            message += ("\nPlease give a valid deadline in the format of yyyy-MM-dd HHmm!"
                        + "\nVeuillez indiquer une date limite valide au format yyyy-MM-dd HHmm!");
        }
        return message;
    }

    /**
     * Greets the user by printing default greeting text.
     */
    public String greet() {
        return ("Hello! I'm Jean!"
                + "\nHow can I help you?"
                + "\nBonjour! Je m'appelle Jean!"
                + "\nVous désirez?");
    }

    /**
     * Bids goodbye to user by printing default ending text.
     */
    public String end() {
        return ("\tGoodbye! See you soon!"
                + "\n\tAu revoir! À tout à l'heure!");
    }
}
