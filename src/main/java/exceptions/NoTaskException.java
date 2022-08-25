package exceptions;

public class NoTaskException extends TumuException {
    private static final String NO_TASK_EXCEPTION =
            "No tasks currently available. " +
                    "Add a task before (un)marking or deleting!";

    public NoTaskException() {
        super(NO_TASK_EXCEPTION);
    }
}
