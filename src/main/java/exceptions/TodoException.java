package exceptions;

/**
 * Exception occurs when a task is not entered during a todo.
 */
public class TodoException extends TumuException {
    private static final String TODO_EXCEPTION = "Please enter a task.";

    /**
     * Constructor for the TodoException class.
     */
    public TodoException() {
        super(TODO_EXCEPTION);
    }

    /**
     * Returns the feedback of asking a user to enter a task.
     * @return The exception message.
     */
    @Override
    public String toString() {
        return TODO_EXCEPTION;
    }
}
