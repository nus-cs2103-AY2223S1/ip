package duke.utils;

import java.util.Scanner;

import duke.exception.DukeException;

/**
 * duke.Duke utility function to handle common outputs to be printed.
 */
public class UI {

    private Scanner reader;

    public UI() {
        reader = new Scanner(System.in);
    }

    /**
     * Prints the duke.Duke welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    /**
     * Reads the input from the user.
     *
     * @return String input.
     */
    public String readCommand(String input) {
        return input;
    }

    /**
     * Prints duke.Duke error messages.
     *
     * @param e Errors related to duke.Duke.
     * @see DukeException
     */
    public String showError(DukeException e) {
        return e.toString();
    }

    public String showTasks() {
        return "Here are the tasks in your list:\n";
    }

    public String showMatchingTasks() {
        return "Here are the matching tasks in your list:\n";
    }

}
