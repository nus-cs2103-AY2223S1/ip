package duke.exception;

import duke.Ui;

/**
 * A DukeDateTimeException is thrown when a user input commands that are invalid.
 */
public class DukeInvalidException extends DukeException {
    public DukeInvalidException() {
        super("OOPS! I'm sorry, but I don't know what that means.\n" + Ui.promptUserInput());
    }
}
