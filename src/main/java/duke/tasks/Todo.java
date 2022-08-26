package duke.tasks;

/**
 * This class encapsulates a to-do task from the user.
 */
public class Todo extends Task {

    /**
     * Constructs a to-do task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskIcon(), super.toString());
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo that = (Todo) o;
            return getDescription().equals(that.getDescription());
        }
        return false;
    }
}
