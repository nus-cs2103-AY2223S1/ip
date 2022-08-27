package duke.task;

import java.time.LocalDate;

/**
 * The abstract superclass of all types of tasks the user can input.
 */
public abstract class Task {
    /**
     * Represents the type of task it is.
     */
    public final TaskType type;
    protected String description;
    protected boolean isDone;
    protected Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a 1 for completed tasks and 0 for uncompleted tasks for writing purposes.
     * @return The integer signifying the task's status.
     */
    public Integer getDoneStatus() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Returns the description of the task for writing purposes.
     * @return The string containing the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns true when the provided date matches the task's date.
     * @param date The given LocalDate.
     * @return The boolean value of whether the date matches.
     */
    public abstract boolean isDateEqual(LocalDate date);

    /**
     * Returns true when the description contains the queried keyword.
     * @param query The keyword queried.
     * @return The boolean value of whether the description contains the keyword.
     */
    public boolean isQueryPresent(String query) {
        return this.description.contains(query);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
