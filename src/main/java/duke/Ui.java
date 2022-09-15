package duke;

import java.util.Scanner;

/**
 * Represents User Interface to interact with user.
 * @author Tan Wen Cong
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads input line by user and returns it as a String
     *
     * @return String command input by user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a line separating next output from current one
     */
    public void showLine() {
        System.out.println("-----------------------------------");
    }

    /**
     * Prints error to output
     *
     * @param de DukeException to be shown in output
     */
    public void showError(DukeException de) {
        System.out.println(de.getMessage());
    }

    /**
     * Prints lines to output at the start of the program to welcome user
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
