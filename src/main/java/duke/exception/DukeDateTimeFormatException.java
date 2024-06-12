package duke.exception;

/**
 * An exception class that is thrown when a task command has an invalid format of date and time.
 * The default input DateTime format should be "yyyy-MM-dd HH:mm:ss".
 * If the user enters "MM/yyyy", then this exception will be thrown.
 */
public class DukeDateTimeFormatException extends DukeException {

    /**
     * The standard constructor.
     */
    public DukeDateTimeFormatException(String message) {
        super(message);
    }
}
