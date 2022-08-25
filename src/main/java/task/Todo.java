package task;

/**
 * Class representing a Todo task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    protected String getTypeIndicator() {
        return "T";
    }

    @Override
    protected String serialize() {
        return super.toJsonObject().toString();
    }
}
