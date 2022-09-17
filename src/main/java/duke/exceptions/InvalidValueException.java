package duke.exceptions;

/**
 * Thrown when an index (accessor to a list/array) is NAN.
 */
public class InvalidValueException extends DukeException {

    private static final String DESCRIPTION = "Hey, %s needs a number!";

    public InvalidValueException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
