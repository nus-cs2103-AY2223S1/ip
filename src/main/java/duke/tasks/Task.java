package duke.tasks;

import java.time.LocalDate;

/**
 * This class encapsulates a task that users can add a list
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a new task.
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To get the task status
     * @return A string that describes the task status. X indicates that the task is completed.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * To mark a task as completed.
     * @return A string that notifies the user if the task has been marked as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return String.format("\tNice! I have marked this task as done:\n\t\t%s",
                this);
    }

    /**
     * To mark a task as not completed.
     * @return A string that notifies the user if the task has been marked as not done.
     */
    public String markAsNotDone() {
        this.isDone = false;
        return String.format("\tOkay! I have marked this task as not done:\n\t\t%s",
                this);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Sets the completion status of the task
     * @param value The completion status
     */
    public void setIsDone(boolean value) {
        this.isDone = value;
    }

    /**
     * Checks if the task is completed
     * @return True if the task is completed
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets the description of the task
     * @return The task's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the date of the task
     * @return The date of the task
     */
    public abstract LocalDate getDate();

    /**
     * Gets the task type
     * @return The task type
     */
    public abstract String getTaskType();
}
