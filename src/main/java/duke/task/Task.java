package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task the user wishes to complete.
 */
public class Task {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, dd LLL yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
            "EEE, dd LLL yyyy, hh:mma"
    );
    private String description;
    private boolean isDone;

    /**
     * TaskType values are used to indicate the type of the Task
     * to retrieve the corresponding information.
     */
    public enum TaskType {
        ToDo, Deadline, Event
    }

    /**
     * Constructs a new task.
     *
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the status of the task to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the status of the task to not done.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Converts the information of the task to a format suitable
     * for saving in a data file.
     *
     * @return A description of the task to be saved in a data file.
     */
    public String fileDescription() {
        if (isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Indicates whether the task occurs on the given date.
     *
     * @param searchDate The date to be checked.
     * @return True if the task occurs on the given date.
     */
    public boolean isOn(LocalDate searchDate) {
        return false;
    }

    /**
     * Indicates whether the task description contains the given search term.
     *
     * @param searchTerm Search term to search for.
     * @return True if the task description contains the given search term.
     */
    public boolean containsSearchTerm(String searchTerm) {
        return this.description.contains(searchTerm);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String done;
        if (isDone) {
            done = "[X]";
        } else {
            done = "[ ]";
        }
        return done + " " + this.description;
    }

}
