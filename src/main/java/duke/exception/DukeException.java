package duke.exception;

/**
 * Class to encapsulate exceptions thrown by program.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param s Part of message to be included in error message.
     */
    public DukeException(String s) {
        super(s);
    }

    /**
     * Constructor for DukeException.
     * Does not require input, error message fixed.
     */
    public DukeException() {
        super();
    }
}
