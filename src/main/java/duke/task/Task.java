package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents an abstract superclass to be inherited by other types of tasks.
 */
public abstract class Task {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_TIME_DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm");

    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructor for a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert !description.isEmpty() : "Description of a task should not be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon to indicate whether the task is done or not done.
     *
     * @return The string representation of the status icon to indicate whether the task is done or not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void maskUndone() {
        this.isDone = false;
    }

    /**
     * Encodes the task into a decodable and readable string representation for storage.
     *
     * @return The encoded string representation of a task for storage
     */
    public String encode() {
        String encodedStatus = isDone ? "1" : "0";
        return this.taskType.getTaskSymbol() + " | " + encodedStatus + " | " + this.description;
    }

    public boolean containsQuery(String query) {
        return this.description.contains(query);
    }

    @Override
    public String toString() {
        return "[" + this.taskType.getTaskSymbol() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
