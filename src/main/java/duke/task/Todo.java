package duke.task;

/**
 * Represents a Task without a specific date/time.
 * @author Aaron Pang
 * @version CS2103T AY22/23 Semester 1
 */
public class Todo extends Task {
    /**
     * Constructs a to-do task without isDone.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Construst a to-do task with isDone.
     * @param description Description of the to-do task.
     * @param isDone True if task is done, false otherwise
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Shows the to-do task description.
     *
     * @return String with the to-do task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveTask() {
        return String.format("T | %s", super.saveTask());
    }
}