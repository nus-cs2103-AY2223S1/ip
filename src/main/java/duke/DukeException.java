package duke;

/**
 * Exception class for when the user enters an invalid input.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param msg Error message.
     */
    public DukeException(String msg) {
        super(msg);
    }

}
