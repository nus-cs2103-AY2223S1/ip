package duke;

import java.util.Scanner;

import duke.response.DukeResponse;
import duke.response.ExceptionResponse;

/**
 * Handles user input.
 */
public class DukeUi {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Gets user input and returns the appropriate response.
     * @param list The task list.
     * @return An appropriate DukeResponse.
     */
    public DukeResponse readInput(DukeList list) {
        String input = scanner.nextLine();
        try {
            return Parser.getResponse(list, input);
        } catch (DukeException e) {
            return new ExceptionResponse(e);
        }
    }

    /**
     * Displays an error.
     * @param e The error to display.
     */
    public void showError(DukeException e) {
        new ExceptionResponse(e).run();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        this.scanner.close();
    }
}
