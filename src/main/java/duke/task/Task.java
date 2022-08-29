package duke.task;

import java.time.LocalDate;

/**
 * The class represents a Task.
 *
 * @author Bryan Ng Zi Hao
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Changes the task to the data format.
     *
     * @return a String representation of the data format.
     */
    public String toDataFormat() {
        return "The data are as follows:";
    }

    /**
     * Checks if the task is due on the date provided.
     *
     * @param date The date the task is due on.
     * @return a boolean value, true if the task is due on said date.
     */
    public boolean isOn(LocalDate date) {
        return false;
    }

    /**
     * Checks if the description contains this keyword.
     *
     * @param string The keyword.
     * @return a boolean value.
     */
    public boolean contains(String string) {
        return description.contains(string);
    }

    /**
     * Override the toString() method to display the task.
     *
     * @return A String representing the task.
     */
    @Override
    public String toString() {
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, description);
    }
}
