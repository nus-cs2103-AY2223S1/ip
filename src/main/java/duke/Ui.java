package duke;

import java.util.Scanner;

/**
 * Class that handles user interface of Duke Bot.
 */
public class Ui {
    public static String LOGO = " ____        _        \n"
                             + "|  _ \\ _   _| | _____ \n"
                             + "| | | | | | | |/ / _ \\\n"
                             + "| |_| | |_| |   <  __/\n"
                             + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;
    private boolean verbose;

    /**
     * Class constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
        verbose = true;
    }

    /**
     * Gets the next line of user input.
     * 
     * @return Next line of user input.
     */
    public String getNextLine() {
        return sc.nextLine();
    }

    /**
     * Prints the logo of Duke Bot.
     */
    public void printLogo() {
        if (verbose) {
            System.out.println("Hello from\n" + LOGO);
        }
    }

    /**
     * Formats and prints a string.
     * 
     * @param m Message string to be printed.
     */
    public void printMessage(String m) {
        String message = "    ____________________________________________________________\n    "
                + m
                + "\n    ____________________________________________________________\n";
        if (verbose) {
            System.out.println(message);
        }
    }

    /**
     * Sets the verbosity of user interface.
     * Verbosity determines whether the user interface prints messages.
     * 
     * @param v Value of verbosity.
     */
    public void setVerbose(boolean v) {
        verbose = v;
    }
}
