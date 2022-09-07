package blob.tasks;

import blob.exception.InvalidPriorityException;
import blob.parser.PriorityParser;

/**
 * The Task class encapsulates a task.
 * A task has a string description describing the task to be done, and a boolean indicator to represent
 * the status of completion of the task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected Priority priority;

    /**
     * Returns a Task given a description, TaskType and priority
     *
     * @param description The given task description.
     * @param type The given TaskType.
     * @param priority The given priority.
     */
    public Task(String description, TaskType type, String priority) throws InvalidPriorityException {
        this.description = description;
        this.type = type;
        this.isDone = false;
        this.priority = new PriorityParser().parse(priority);
    }

    /**
     * Returns an icon representing the state of completion of the task.
     *
     * @return A checkmark icon (✓) if the task is done, and " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task
     */
    public String toString() {
        return String.format("<%s>[%s][%s] %s", this.priority.toString(), this.type.getAbbreviation(),
                this.getStatusIcon(), this.description);
    }

    /**
     * Returns the formatted task details for storing in text file.
     *
     * @return Formatted task details for storing in text file
     */
    public String toFileString() {
        return String.format("%s | %s | %d | %s", this.priority.getAbbreviation(), this.type.getAbbreviation(),
                this.isDone ? 1 : 0, this.description);
    }

}
