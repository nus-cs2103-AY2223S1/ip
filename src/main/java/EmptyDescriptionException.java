public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public EmptyDescriptionException(String message) {
        super(message);
    }
}
