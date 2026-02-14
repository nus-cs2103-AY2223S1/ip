package duke.exceptions;

/**
 * Represents an InvalidItemException which is thrown when
 * an attempt is made to access or modify a non-existent item.
 */
public class InvalidItemException extends Exception {

    /**
     * Constructs an InvalidItemException with Duke Aemon's standard message.
     */
    public InvalidItemException() {
        super("You reach for what is not there. Choose a number within the bounds of your list.");
    }

    /**
     * Constructs an InvalidItemException with a custom message.
     *
     * @param msg Custom exception message
     */
    public InvalidItemException(String msg) {
        super(msg);
    }
}
