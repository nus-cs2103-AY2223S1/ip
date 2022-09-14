package duke;

/**
 * Encapsulates a Duke Exception for inputting the wrong date format.
 *
 */
public class DateNotRecognisedException extends DukeException {
    public DateNotRecognisedException() {
        super("Please input date in following format: YYYY-MM-DD");
    }
}
