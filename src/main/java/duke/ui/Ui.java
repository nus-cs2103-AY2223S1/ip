package duke.ui;

import duke.common.DukeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles interaction with the user.
 * Collects user input and provides responses to the user.
 *
 * @author Tan Jun Wei
 */
public class Ui {
    private final InputStreamReader inputStreamReader;
    private final BufferedReader bufferedReader;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    /**
     * Displays a loading error to the user.
     */
    public void showLoadingError() {
        System.out.println("Duke is unable to load saved file");
    }

    /**
     * Reads the user input.
     *
     * @throws DukeException If unable to get user input.
     */
    public String readCommand() throws DukeException {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new DukeException("Duke is unable to read your input");
        }
    }

    /**
     * Displays a string to the user.
     *
     * @param output The string to be displayed.
     */
    public void showOutput(String output) {
        System.out.println(output);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a dotted line to the user.
     */
    public void showLine() {
        System.out.println("-----------------------");
    }
}
