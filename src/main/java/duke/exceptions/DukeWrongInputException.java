package duke.exceptions;

/**
 * Represents a DukeWrongInputException.
 * Occurs when a non-integer argument is supplied where integer is expected.
 */
public class DukeWrongInputException extends DukeException {
    private static final String MESSAGE = "The additional argument for %s is of the wrong data type.\n";

    public DukeWrongInputException(String command) {
        super(String.format(MESSAGE, command));
    }
}
