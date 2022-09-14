package duke.task;

import duke.Date;

/**
 * Represents a skeleton for a Task Class.
 * @author Jason
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Date date;

    /**
     * Constructs a Task object.
     * @param description Description of the task object.
     */
    public Task(String description, Date date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    /**
     * Provides the current "marked" status of a task.
     * @return Marked status of task.
     */
    public String getStatusIcon() {
        //Mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of current task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks current task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Writes this event task into the save file format.
     * @return String to be stored in save file.
     */
    public abstract String saveData();

    /**
     * Returns the date of the Task object, if there is no date return null.
     * @return Date of task object.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Compares 2 Tasks based on their dates.
     * @param   anotherTask - The task to be compared.
     * @return  A negative integer, zero, or a positive integer as this task
     *          is less than, equal to, or greater than the supplied task object.
     */
    public int compareTo(Task anotherTask) {
        return this.getDate().compareTo(anotherTask.getDate());
    }
}
