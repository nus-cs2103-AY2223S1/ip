package duke.controller;

import java.util.Scanner;

/**
 * Represents a user interface class to read in inputs from the command line
 * and display messages to the command line.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner sc;
    private MainWindow window;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.window = new MainWindow();
    }

    /**
     * Reads in user input from the command line.
     * @return String response of the input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print given message in a nice format.
     * @param msg Message to be printed.
     */
    public static void showMsg(String msg) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(msg);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print given error message in a nice format.
     * @param error Error message to be printed.
     */
    public static void showError(String error) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(error);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print welcome message at the start of the program.
     */
    public void showWelcomeMsg() {
        /*
        System.out.println(HORIZONTAL_LINE);
        System.out.println(LOGO);
        System.out.println("How may I help you?");
        System.out.println(HORIZONTAL_LINE);
         */
        window.showWelcome(LOGO);
    }
}
