package ava.task;

import java.time.LocalDateTime;

/**
 * Abstract class to represent "DatedTask" tasks.
 */
public abstract class DatedTask extends Task {
    protected LocalDateTime time;

    /**
     * The constructor for DatedTask.
     *
     * @param description Task description.
     * @param time The time of the task.
     */
    protected DatedTask(String description, LocalDateTime time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Time getter.
     *
     * @return The time attribute of the task.
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Compares dates.
     *
     * @param otherTask The other task to be compared to.
     * @return A number greater than 0 if the date of the Task precedes the other Task's date,
     *         0 if the dates are the same, a number smalled than 0 otherwise.
     */
    public int compareTo(Task otherTask) {
        assert otherTask instanceof DatedTask;
        DatedTask task = (DatedTask) otherTask;
        return time.compareTo(task.time);
    }
}
