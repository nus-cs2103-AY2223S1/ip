package duke.exceptions;

/**
 * Representation of an exception where
 * user event input does not meet specified format.
 */
public class ImproperEventFormatException extends DukeException {

    /**
     * Provide user with the specified format to create
     * an Event via Duke.
     */
    public ImproperEventFormatException() {
        super("IMPROPER EVENT FORMAT. KINDLY FOLLOW:\n"
                + "event task /at YYYY-MM-DD hh:mm\n"
                + "i.e event cry"
                + " /at 2000-10-10 23:00");
    }
}
