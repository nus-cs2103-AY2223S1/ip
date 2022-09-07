package duke;

/**
 * Exception when input given by user is invalid.
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
