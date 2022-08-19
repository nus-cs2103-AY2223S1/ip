package exceptions;

/**
 * {@code DukeException} is the superclass of those
 * exceptions that can be thrown during the operation of the Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
