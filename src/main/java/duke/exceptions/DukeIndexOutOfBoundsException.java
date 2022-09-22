package duke.exceptions;

/**
 * Represents an index out of bounds exception made for <code>Duke</code>.
 */
public class DukeIndexOutOfBoundsException extends DukeException {

    /**
     * Constructs a <code>DukeIndexOutOfBoundsException</code> with the given error message.
     *
     * @param msg Error message that corresponds to the exception.
     */
    public DukeIndexOutOfBoundsException(String msg) {
        super(msg);
    }

}
