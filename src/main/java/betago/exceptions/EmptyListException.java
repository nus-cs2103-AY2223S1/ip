package betago.exceptions;

/**
 * EmptyListException that is thrown when list is currently empty.
 */
public class EmptyListException extends Exception {
    public EmptyListException(String message) {
        super(message);
    }
}
