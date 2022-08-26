package duke.exceptions;

/**
 * Thrown when the given datetime format is not recognizable.
 */
public class InvalidTimeFormatException extends DukeException {

    private static final String DESCRIPTION = "%s is not recognised as a time stamp";

    public InvalidTimeFormatException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
