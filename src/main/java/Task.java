import java.time.format.DateTimeFormatter;

/**
 * A task has a description and a status tracking whether it is done or not.
 * Tasks cannot be instantiated directly - they should be inherited by a subclass.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "d/M/yy HH:mm");
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a checkmark `✓` if the task is done, an empty string otherwise.
     *
     * @return the status icon
     */
    private String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
