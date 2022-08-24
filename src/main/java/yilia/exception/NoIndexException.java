package yilia.exception;

/**
 * Represents an exception to be thrown when the given index is not given.
 */
public class NoIndexException extends Exception {
    private String operation;
    public NoIndexException(String operation) {
        this.operation = operation;
    }
    @Override
    public String getMessage() {
        return "Please specify the index of items to " + operation;
    }
}
