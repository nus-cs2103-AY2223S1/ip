package duke.task;

/**
 * Represents a Todo task.
 */
public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encodeToString() {
        String taskStatus = this.isDone ? "Done" : "Undone";
        return String.format("T | %s | %s", taskStatus, this.description);
    }
}
