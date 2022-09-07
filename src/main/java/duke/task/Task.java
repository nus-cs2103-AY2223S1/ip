package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public abstract String getTaskType();

    public abstract String getDescription();

    public abstract void updateDate(LocalDate date) throws DukeException;

    /**
     * Returns icon of marked or unmarked status of the task.
     *
     * @return Icon of the task status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setAsDone() {
        isDone = true;
    }

    public void setAsUndone() {
        isDone = false;
    }

    /**
     * Updates the description of the task.
     *
     * @param desc String contains the new description
     */
    public void updateTask(String desc) {
        description = desc;
    }

    /**
     * Return string representation of task.
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }


}
