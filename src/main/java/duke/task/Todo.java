package duke.task;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {

    /**
     * Returns a Todo object.
     *
     * @param taskName Name of the task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "T" + "|" + (this.getTaskStatus() ? "1" : "0") + "|" + this.getPriorityNumber() + "|" + this.getTaskName();
    }
}
