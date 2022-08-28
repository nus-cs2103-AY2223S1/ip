package duke;

/**
 * A custom exception class specifically used for exceptions incurred
 * by the methods of the class.
 */
public class DukeException extends Exception {

    /**
     * The class constructor for a DukeException.
     *
     * @param message description of the error.
     */
    public DukeException(String message) {
        super(message);
    }

}
