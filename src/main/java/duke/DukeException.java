package duke;

/**
 * A custom Exception class for application-specific exceptions.
 */
public class DukeException extends Exception{

    /**
     * Creates an instance of DukeException.
     *
     * @param msg The exception message to be shown to the user.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
