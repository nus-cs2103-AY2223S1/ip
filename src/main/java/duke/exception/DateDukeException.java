package duke.exception;

/**
 * DateDukeException is thrown if the user inputs the date in the incorrect format
 */
public class DateDukeException extends DukeException {
    public DateDukeException() {
        super("Please fill in the date in this format yyyy-mm-dd");
    }
}
