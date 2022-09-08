package seedu.duke.Task;

import seedu.duke.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a new Task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Updates the specified category of this Task
     *
     * @param category the category to be updated
     * @param details the update
     * @return this Task
     */
    public Task update (String category, String details) {
        if ("description".equals(category)) {
            this.description = details;
        }
        return this;
    }

    /**
     * Sets the status icon of this Task
     */
    public void setStatusIcon(boolean b) {
        this.isDone = b;
    }

    /**
     * Returns the status icon of this Task
     *
     * @return the status icon of this Task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the short form of the type of this Task
     *
     * @return the short form of the type of this Task
     */
    abstract public String getType();

    /**
     * Returns the long form of the type of this Task
     *
     * @return the long form of the type of this Task
     */
    abstract public String getTypeLong();

    /**
     * Returns the status of this Task
     *
     * @return the status of this Task
     */
     public String getStatus() {
        if (this.isDone) {
            return "1";
        }
        return "0";
    }

    /**
     * Determine if the task description contains a specified substring
     *
     * @param s specified substring
     * @return a boolean stating if the task description contains a specified substring
     */
    public Boolean contains(String s) {
        return this.description.contains(s);
    }

    /**
     * Returns the String object representing this Task
     *
     * @return the String object representing this Task
     */
    abstract public String toString();

    /**
     * Returns the String object to be stored as data representing this Task
     *
     * @return the String object to be stored as data representing this Task
     */
    abstract public String getData();
}
