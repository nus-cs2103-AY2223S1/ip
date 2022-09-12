package stashy.data.exception;

/**
 * Developer-specific exception class.
 */
public class DeveloperException extends StashyException {
    public DeveloperException(String message) {
        super("Note to dev: " + message);
    }
}
