package duke;

import java.util.Scanner;

/**
 * Class that handles user interface of Duke Bot.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;
    private boolean isVerbose;

    /**
     * Class constructor for Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        isVerbose = true;
    }

    /**
     * Gets the next line of user input.
     *
     * @return Next line of user input.
     */
    public String getNextLine() {
        return scanner.nextLine();
    }

    /**
     * Prints the logo of Duke Bot.
     */
    public void printLogo() {
        if (isVerbose) {
            System.out.println("Hello from\n" + LOGO);
        }
    }

    /**
     * Formats and prints a string.
     *
     * @param message Message string to be printed.
     * @return The formatted and printed message.
     */
    public String printMessage(String message) {
        String formattedMessage = "    __________________________________________________\n    "
                + message
                + "\n    __________________________________________________\n";
        if (isVerbose) {
            System.out.println(formattedMessage);
        }
        return formattedMessage;
    }

    /**
     * Sets the verbosity of user interface.
     * Verbosity determines whether the user interface prints messages.
     *
     * @param verbosity Value of verbosity.
     */
    public void setVerbose(boolean verbosity) {
        isVerbose = verbosity;
    }
}
