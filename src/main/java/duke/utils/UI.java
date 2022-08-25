package duke.utils;

import duke.exception.DukeException;
import java.util.Scanner;

public class UI {
    Scanner reader;

    public UI() {
        reader = new Scanner(System.in);
    }

    /**
     * Prints the Duke welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads the input from the user.
     * @return String input.
     */
    public String readCommand() {
        return reader.nextLine();
    }

    /**
     * Prints Duke error messages.
     * @param e Errors related to Duke.
     * @see DukeException
     */
    public void showError(DukeException e) {
        System.out.println(e);
    }
}
