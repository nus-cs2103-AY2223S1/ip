package duke.exceptions;

/**
 * This class encapsulates a DukeException caused by error in parsing datetime formats.
 */
public class DukeDateTimeParseException extends DukeException {

    /**
     * Constructs a DukeDateTimeParseException exception.
     */
    public DukeDateTimeParseException() {
        super("Exception: Cannot parse datetime", "I can't believe there is something I don't understand!\n"
                + "If you are trying to tell me a date, follow the following format:\n"
                + "yyyy-MM-dd HHmm");
    }
}
