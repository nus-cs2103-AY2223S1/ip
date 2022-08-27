package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract superclass of a task that a user has.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private String description;
    private boolean isDone;

    /**
     * Constructor for {@code Task}.
     *
     * @param description Description of the {@code Task}.
     * @param isDone Completion status of the {@code Task}.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructor for {@code Task}. Assumes completion status is {@code false}.
     *
     * @param description Description of the {@code Task}.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the {@code isDone} status variable to {@code true}.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the {@code isDone} status variable to {@code false}.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Checks if the {@code Task} description contains the keyword provided.
     *
     * @param keyword
     * @return {@code true} if keyword is present in description, {@code false} otherwise.
     */
    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns a String representation of the {@code Task} in storage format.
     */
    public String getStorageFormat() {
        return this.getStatusBit() + " | " + this.description;
    }

    /**
     * Returns {@code true} if {@code Task} is done, otherwise {@code false}.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns 1 if {@code Task} is done, otherwise 0.
     */
    int getStatusBit() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns a String representation of {@code isDone} status variable in display format.
     */
    String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a String representation of {@code Task} in display format.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
