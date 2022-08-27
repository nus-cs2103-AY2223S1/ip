package duke.ui;

import java.util.Scanner;

public class Ui {
    /* Rem bot logo */
    public final String LOGO = " ___  ___  __ __ \n"
            + "| . \\| __>|  \\  \\\n"
            + "|   /| _> |     |\n"
            + "|_\\_\\|___>|_|_|_|\n";

    /* line filler */
    public final String SPACER = "----------------------------------------------------";

    /* Welcome message */
    public final String WELCOME = "Konnichiwa! I'm Rem desu! :>\n"
            + "What can I do for you today?\n";

    /* Goodbye message */
    public final String GOODBYE = "See you soon! <3";
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
        System.out.println(LOGO + "\n" + WELCOME + SPACER);
    }

    /**
     * Prints the goodbye message when bot is terminated.
     */
    public void showGoodbye() {
        System.out.println(SPACER + "\n" + GOODBYE);
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