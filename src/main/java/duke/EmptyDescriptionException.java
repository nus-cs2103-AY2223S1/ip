package duke;

public class EmptyDescriptionException extends RuntimeException {
    public EmptyDescriptionException(String message) {
        super("☹ OOPS!!! The description of a " + message + " cannot be empty.");
    }
}