/**
 * This class is used to construct a task.
 */
package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    /** Description of the task */
    protected String description;

    /** Status of the task */
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description The task description.
     * */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return String representation of the status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
     * Formats the task for writing to file.
     *
     * @return String containing format for saving.
     */
    public String formatToSave() {
        return "";
    }

    /**
     * Checks if task description contains a string
     *
     * @param str String to check against.
     * @return true if it contains and false otherwise.
     */
    public boolean contains(String str) {
        return this.description.contains(str);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}