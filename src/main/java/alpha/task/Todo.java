package alpha.task;

/**
 * Represents todo type task.
 */
public class Todo extends Task {

    /**
     * Constructor to initialise the global variables.
     *
     * @param description To initialise the task description.
     * @param taskType To initialise the task type.
     */
    public Todo(String description, String taskType) {
        super(description, taskType);
    }

    /**
     * {@inheritDoc}
     *
     * Checks the equality of two objects.
     * Returns true if both objects are instance of Todo class and have the same attributes.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Todo) {
            Todo t = (Todo) obj;
            return (t.getDescription().equals(this.getDescription()) && t.getStatus().equals(this.getStatus())
                    && t.getTaskType().equals(this.getTaskType()));
        }
        return false;
    }
}
