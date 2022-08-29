package chad.task;

/**
 * chad.task.Todo task that is child of task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
