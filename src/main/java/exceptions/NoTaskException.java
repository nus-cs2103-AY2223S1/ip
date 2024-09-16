package exceptions;

/**
 * Exception occurs when no tasks are available during
 * marking, unmarking or deleting of a task.
 */
public class NoTaskException extends TumuException {
    private static final String NO_TASK_EXCEPTION =
            "No tasks currently available. "
                    + "Add a task before (un)marking or deleting!";

    /**
     * Constructor for the NoTaskException class.
     */
    public NoTaskException() {
        super(NO_TASK_EXCEPTION);
    }
}
