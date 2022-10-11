package seedu.duke.exception;

/**
 * Exception class to be used when too many arguments are inputted
 */
public class TooManyArgumentsException extends DukeException {
    public TooManyArgumentsException(String command) {
        super("Please input only '" + command + "' and a number, Master.");
    }
}
