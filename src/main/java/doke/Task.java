package doke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent the abstract class Task
 */
public abstract class Task implements Comparable<Task> {

    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private String desc;
    private boolean isDone;

    /**
     * a constructor for Task class.
     *
     * @param desc description for the task
     */
    Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    @Override
    public int compareTo(Task task) {
        if (getType().equals("T")) {
            return 1;
        } else if (task.getType().equals("T")) {
            return -1;
        } else {
            return this.getTime().compareTo(task.getTime());
        }
    }
    /**
     * Returns a String representing the type of the task.
     *
     * @return Task type
     */
    public abstract String getType();

    /**
     * Returns a String representation of the done status of the task.
     *
     * @return either "X" if the task is marked done or " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns an int to signify if the task is done or not.
     *
     * @return 1 if the task is marked done or 0 otherwise.
     */
    public int getStatus() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns a String of the description of the task.
     *
     * @return a description of the task.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns a LocalDate object related to the task.
     *
     * @return a LocalDate related to the task.
     */
    public abstract LocalDate getTime();

    /**
     * Marks the task as done(isDone = true).
     *
     * @throws DokeException
     */
    public void markDone() throws DokeException {
        if (isDone) {
            throw new DokeException();
        }
        isDone = true;
    }

    /**
     * Marks the task as done(isDone = false).
     *
     * @throws DokeException
     */
    public void markNotDone() throws DokeException {
        if (!isDone) {
            throw new DokeException();
        }
        isDone = false;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return a string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]   " + desc;
    }

}
