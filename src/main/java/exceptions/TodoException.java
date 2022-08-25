package exceptions;

public class TodoException extends TumuException {
    private static String TODO_EXCEPTION = "Please enter a task.";

    public TodoException() {
        super(TODO_EXCEPTION);
    }

    @Override
    public String toString() {
        return TODO_EXCEPTION;
    }
}
