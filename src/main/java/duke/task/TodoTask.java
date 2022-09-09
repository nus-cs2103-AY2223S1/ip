package duke.task;

/**
 * Represents a task without a date or time.
 */
public class TodoTask extends Task {

    /**
     * Creates a new task without a date or time.
     *
     * @param description Task that needs to be done.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (isCompleted()) {
            return String.format("[T][1] %s", getTaskDescription());
        } else {
            return String.format("[T][0] %s", getTaskDescription());
        }
    }

}
