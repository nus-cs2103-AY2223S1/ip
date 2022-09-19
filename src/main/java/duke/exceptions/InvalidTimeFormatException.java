package duke.exceptions;

/**
 * Thrown when the given datetime format is not recognizable.
 */
public class InvalidTimeFormatException extends DukeException {

    private static final String DESCRIPTION =
            "I don't recognise %s a time stamp. I only know MMM d yyyy, dd/MM/yyyy, dd-MM-yyyy ";

    public InvalidTimeFormatException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
