package task;

/**
 * Encapsulates a task that to be done.
 *
 * @author Marcus Low
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description, false);
    }

    /**
     * Constructs a Todo task.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }
    }

    @Override
    public String toStringText() {
        return "T | " + this.description + " | " + this.isDone;
    }
}
