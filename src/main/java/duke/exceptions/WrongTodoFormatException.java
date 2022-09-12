package duke.exceptions;

/**
 * Represents an exception due to a wrong todo format.
 */
public class WrongTodoFormatException extends WrongFormatException {
    private static final String CORRECT_TODO_SYNTAX = "todo <description>";

    /**
     * Constructs a wrong todo format exception.
     */
    public WrongTodoFormatException() {
        super(CORRECT_TODO_SYNTAX);
    }
}
