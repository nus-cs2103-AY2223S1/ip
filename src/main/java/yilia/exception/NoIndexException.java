package yilia.exception;

/**
 * Represents an exception to be thrown when the given index is not given.
 */
public class NoIndexException extends Exception {
    private String operation;
    public NoIndexException(String operation) {
        this.operation = operation;
    }
    /**
     * Returns the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage() {
        return "Please specify the index of items to " + operation;
    }
}
