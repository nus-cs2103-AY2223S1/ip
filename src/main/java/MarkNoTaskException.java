public class MarkNoTaskException extends MarkException {
    private static final String NO_TASK_EXCEPTION =
            "\tNo tasks currently available. Add a task before marking!";

    public MarkNoTaskException() {
        super(NO_TASK_EXCEPTION);
    }

    public MarkNoTaskException(String message) {
        super(message);
    }
}
