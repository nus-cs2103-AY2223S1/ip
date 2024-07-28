package duke;

import duke.response.DukeResponse;
import duke.response.ExceptionResponse;

/**
 * Handles user input.
 */
public class DukeUi {
    /**
     * Gets user input and returns the appropriate response.
     *
     * @param input The user input.
     * @param list The task list.
     * @return An appropriate DukeResponse.
     */
    public DukeResponse readInput(String input, DukeList list) {
        try {
            return Parser.getResponse(list, input);
        } catch (DukeException e) {
            return new ExceptionResponse(e);
        }
    }
}
