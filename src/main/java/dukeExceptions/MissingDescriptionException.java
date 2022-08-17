package dukeExceptions;

// Thrown when there is a missing description
public class MissingDescriptionException extends Exception {
    public MissingDescriptionException(String command) {
        super(String.format("OOPS!!! The description of a %s cannot be empty.", command));
    }
}
