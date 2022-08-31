package duke.ui;

import java.util.Scanner;

import duke.common.Messages;

/**
 * Represents the user interface of the Duke program.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the starting message when bot is launched.
     */
    public void showWelcome() {
        System.out.println(Messages.LOGO + "\n" + Messages.WELCOME + Messages.SPACER);
    }

    /**
     * Prints the goodbye message when bot is terminated.
     */
    public void showGoodbye() {
        System.out.println(Messages.SPACER + "\n" + Messages.GOODBYE);
    }

    /**
     * Prints the error message when an error occurs.
     * @param error the error message to be printed
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Reads the string of command from the user before hitting 'enter' key.
     * @return the string of command entered by the user before hitting 'enter' key
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
