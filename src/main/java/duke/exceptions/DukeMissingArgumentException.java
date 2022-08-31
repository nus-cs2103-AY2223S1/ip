package duke.exceptions;

/**
 * Represents a missing argument exception made for <code>Duke</code>.
 */
public class DukeMissingArgumentException extends DukeException {

    /**
     * Constructs a <code>DukeMissingArgumentException</code> with the given error message.
     *
     * @param msg Error message that corresponds to the exception.
     */
    public DukeMissingArgumentException(String msg) {
        super(msg);
    }

}
