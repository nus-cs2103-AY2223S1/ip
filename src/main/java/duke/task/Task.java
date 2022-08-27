package duke.task;

import java.time.LocalDate;

/**
 * The Task class represents a task
 * added to the storage.
 */
public abstract class Task {

    private String description;
    private Boolean isDone;


    /**
     * Construct a Task object.
     * @param description description for Task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Return an icon that represents whether the task is done.
     * @return String representation of status.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Return the Task in the form it is stored in local.
     * @return Task in store form.
     */
    public String toStore() {
        return this.isDone ? "1" : "0" + " | " + this.description;
    }

    /**
     * Compare date between tasks.
     * * @param date Specified date of task.
     * @return true only if both task on the same date.
     */
    public abstract boolean compareDate(LocalDate date);


    /**
     * Overriding method of toString() for duke.task.Task.
     * @return the string representing duke.task.Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
