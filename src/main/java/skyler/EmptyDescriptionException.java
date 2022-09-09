package skyler;

/**
 * Represents an exception that is thrown when no input task is detected following a command
 */
public class EmptyDescriptionException extends SkylerException {
    @Override
    public String getMessage() {
        return "No input task";
    }
}
