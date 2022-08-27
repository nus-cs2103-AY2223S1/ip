package duke;

/**
 * Exception for tasks without a description.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}