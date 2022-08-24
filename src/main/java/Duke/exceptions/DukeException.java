package Duke.exceptions;

/**
 * {@code DukeException} is the superclass of those
 * exceptions that can be thrown during the operation of the Duke.
 */
public class DukeException extends Exception {
    /**
     * This exception is the super class of all exceptions thrown by duke.
     *
     * @param message Is a string that will be displayed to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
