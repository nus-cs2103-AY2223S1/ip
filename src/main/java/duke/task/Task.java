package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * This abstract class encapsulates the logic of a task.
 */
public abstract class Task {
    /* Task Information */
    protected String description;
    protected boolean isDone;
    /* Constant fields representing the input and output format of the dates. */
    public final static DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public final static DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor for a Task.
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation for the task's current status.
     * @return a string representation of the task's status.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Override toString method for a Task.
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Formats the task into a suitable format for files
     *
     * @return String representation of the Task in saved format.
     */
    public String saveFormat() {
         return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Getter for the description of the task.
     *
     * @return String representation of the description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
