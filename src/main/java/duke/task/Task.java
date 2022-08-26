package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract superclass for handling Tasks.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    private final String description;
    private boolean isComplete;

    /**
     * Constructor for Task.
     *
     * @param description Description of Task.
     * @param isComplete Whether Task is complete.
     */
    Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Constructor for Task. {@code isComplete} defaults to {@code true}.
     *
     * @param description Description of Task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Sets {@code isComplete} to {@code true}
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Sets {@code isComplete} to {@code false}
     */
    public void unmark() {
        this.isComplete = false;
    }

    /**
     * Returns Task description.
     *
     * @return Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns {@code isComplete} status String.
     *
     * @return {@code isComplete} status String.
     */
    String getStatusIcon() {
        return isComplete ? "[X]" : "[ ]";
    }

    /**
     * Returns {@code isComplete} status in integer format.
     *
     * @return {@code isComplete} status.
     *         {@code 0} signfies incomplete. {@code 1} signifies complete.
     */
    int getStatusNumber() {
        return isComplete ? 1 : 0;
    }

    /**
     * Converts Task to a String format used by Storage.
     *
     * @return Storage-readable String.
     */
    public String toStorageFormat() {
        return this.getStatusNumber() + " | " + this.description;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), description);
    }
}
