package duke.exception;

/**
 * Customised Exception for invalid index input.
 */
public class InvalidIndexException extends Exception {

    /**
     * Creates an InvalidIndexException.
     * @param indx Number that is out of bounds.
     */
    public InvalidIndexException(String indx) {
        super("Index " + indx + " is out of bounds, please try again.");
    }
}
