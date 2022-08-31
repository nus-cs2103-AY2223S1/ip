package duke.task;

/**
 * Represents a task-type with description and completion status.
 *
 * @author Tan Jun Wei
 */
public class ToDo extends Task {
    /**
     * Constructs a new to-do task (with default completion status).
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new to-do task (with specified completion status).
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return "T" + Task.ENCODING_SEPARATOR + super.encode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}