public class MarkNoTaskException extends TumuException {
    private static final String NO_TASK_EXCEPTION =
            "\tNo tasks currently available. Add a task before (un)marking!";

    public MarkNoTaskException() {
        super(NO_TASK_EXCEPTION);
    }
}
