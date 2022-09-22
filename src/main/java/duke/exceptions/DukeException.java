package duke.exceptions;

/**
 * Represents an exception made for <code>Duke</code>.
 */
public class DukeException extends Exception {

    /**
     * Constructs a <code>DukeException</code> with the given error message.
     *
     * @param msg Error message that corresponds to the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }

}
