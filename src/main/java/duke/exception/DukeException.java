package duke.exception;

/**
 * Represent all customize exceptions in Duke.
 */
public class DukeException extends Exception{

    /**
     * Constructor for DukeException class.
     *
     * @param message The message to be thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
