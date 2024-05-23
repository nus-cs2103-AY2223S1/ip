package duke.exceptions;

/**
 * Represents a DukeMissingInputException.
 * Occurs when a command is not supplied sufficient arguments.
 */
public class DukeMissingInputException extends DukeException {
    private static final String MESSAGE = "The additional argument for %s cannot be empty.\n";

    public DukeMissingInputException(String command) {
        super(String.format(MESSAGE, command));
    }
}
