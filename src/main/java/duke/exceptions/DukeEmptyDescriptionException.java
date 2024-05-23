package duke.exceptions;

/**
 * Represents a DukeEmptyDescriptionException.
 * Occurs when a user enters whitespace for Task Description.
 */
public class DukeEmptyDescriptionException extends DukeException {

    private static final String MESSAGE = "The description for a %s cannot be empty.\n";

    public DukeEmptyDescriptionException(String command) {
        super(String.format(MESSAGE, command));
    }
}
