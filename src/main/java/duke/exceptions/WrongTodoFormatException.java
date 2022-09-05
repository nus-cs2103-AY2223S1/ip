package duke.exceptions;

/**
 * Represents an exception due to a wrong todo format.
 */
public class WrongTodoFormatException extends WrongFormatException {
    private static final String message = "Description of Todo cannot be empty!";

    /**
     * Constructor for an invalid index exception.
     */
    public WrongTodoFormatException() {
        super(message);
    }
}
