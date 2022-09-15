package duke;

/**
 * InvalidDateException is an exception that is thrown when user enter invalid date.
 */
public class InvalidDateException extends DukeException {

    public InvalidDateException() {
        super("Invalid date format, please enter date in yyyy-mm-dd format.");
    }
}
