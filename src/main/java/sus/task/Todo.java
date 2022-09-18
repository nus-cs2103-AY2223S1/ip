package sus.task;

/**
 * Represents a to-do.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String encodeToString() {
        String taskStatus = isDone ? "1" : "0";
        return String.format("T | %s | %s ", taskStatus, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
