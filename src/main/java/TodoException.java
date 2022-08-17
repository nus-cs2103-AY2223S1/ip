public class TodoException extends TumuException {
    private static String TODO_EXCEPTION = "\tPlease enter a task.";

    public TodoException() {
        super(TODO_EXCEPTION);
    }

    @Override
    public String toString() {
        return TODO_EXCEPTION;
    }
}
