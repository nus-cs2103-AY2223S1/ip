package duke.exceptions;

/**
 * Thrown when an index (accessor to a list/array) is NAN.
 */
public class InvalidValueException extends DukeException {

    private static final String DESCRIPTION = "%s takes in the index of the list as a integer!";

    public InvalidValueException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
