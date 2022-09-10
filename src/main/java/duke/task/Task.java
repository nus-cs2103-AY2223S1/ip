package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract superclass for handling Tasks.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    protected static final boolean IS_INCOMPLETE = false;

    private static final int STATUS_COMPLETE_INT = 1;
    private static final String STATUS_COMPLETE_STRING = "[X]";
    private static final int STATUS_INCOMPLETE_INT = 0;
    private static final String STATUS_INCOMPLETE_STRING = "[ ]";
    private static final String STRING_FORMAT_STORAGE = "%s | %s";
    private static final String STRING_FORMAT_DISPLAY = "%s %s";

    private String description;
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
        isComplete = true;
    }

    /**
     * Sets {@code isComplete} to {@code false}
     */
    public void unmark() {
        isComplete = IS_INCOMPLETE;
    }

    /**
     * Sets {@code description} to {@code updatedDescription}
     */
    public void setDescription(String updatedDescription) {
        this.description = updatedDescription;
    }

    /**
     * Returns Task description.
     *
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns {@code isComplete} status String.
     *
     * @return {@code isComplete} status String.
     */
    String getStatusIcon() {
        return isComplete ? STATUS_COMPLETE_STRING : STATUS_INCOMPLETE_STRING;
    }

    /**
     * Returns {@code isComplete} status in integer format.
     *
     * @return {@code isComplete} status.
     * {@code 0} signifies incomplete. {@code 1} signifies complete.
     */
    int getStatusNumber() {
        return isComplete ? STATUS_COMPLETE_INT : STATUS_INCOMPLETE_INT;
    }

    /**
     * Converts Task to a String format used by Storage.
     *
     * @return Storage-readable String.
     */
    public String toStorageFormat() {
        return String.format(STRING_FORMAT_STORAGE, getStatusNumber(), description);
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT_DISPLAY, this.getStatusIcon(), description);
    }
}
