package duke.exception;

/**
 * An Exception class that encapsulates the situation where the user has given a wrong date format/date format that
 * is not supported in current version of Duke.
 */
public class IllegalDateFormatException extends IllegalInputException {
    public IllegalDateFormatException() {
        super("I could not recognised the date you entered.");
    }
}
