package duke.exception;

/**
 * Represents an exception when input index is out of bounds
 */
public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
        super("The task you are referring to doesn't exist!!\n");
    }
}
