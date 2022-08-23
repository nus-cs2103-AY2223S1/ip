package task;

/**
 * Class representing a Todo task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getTypeIndicator() {
        return "T";
    }
}
