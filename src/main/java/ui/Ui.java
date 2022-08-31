package ui;

import exceptions.DukeException;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    /**
     * Constructs a new UI object
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    private void printSeparator() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints a single string with separators and padding.
     * @param msg String to be printed.
     */
    public void printMsg(String msg) {
        printSeparator();
        System.out.println("     " + msg);
        printSeparator();
    }

    /**
     * Prints multiple strings with separators and padding.
     * @param msgs Strings to be printed.
     */
    public void printMultiMsg(String[] msgs) {
        printSeparator();
        for (String msg : msgs) {
            System.out.println("     " + msg);
        }
        printSeparator();
    }

    /**
     * Reads a single line from the standard input.
     * @return String read from input.
     * @throws DukeException If reading input fails.
     */
    public String readCommand() throws DukeException {
        try {
            return sc.nextLine();
        } catch (Exception e) {
            throw new DukeException("No more input");
        }
    }

    /**
     * Prints welcome message to the screen.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printMultiMsg(new String[] { "Hello my name is Duke", "What can I do for you?" });
    }

    /**
     * Prints error message to screen with formatting.
     * @param msg
     */
    public void showError(String msg) {
        printMsg("☹ OOPS!!! " + msg);
    }

    /**
     * Prints error that file cannot be loaded to screen.
     */
    public void showLoadingError() {
        showError("File could not be loaded. Creating new file.");
    }
}
