package duke.task;

import duke.DukeException;

import java.time.LocalDate;

/**
 * Represents a task.
 *
 * @author Derrick Khoo
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code>.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task, whether it is done or not.
     *
     * @return X if it is done, whitespace if it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the status of the task, denoted by the boolean variable isDone,
     * as true if the task is done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the status of the task, denoted by the boolean variable isDone,
     * as false if the task is undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string description of the task for saving the task
     * to the hard disk.
     *
     * @return the string description
     */
    public String toFileDescription() {
        return (this.isDone ? "1 " : "0 ") + "| " + this.description;
    }

    /**
     * Returns a task from the string description stored in the hard disk.
     *
     * @param input the string description of the task
     * @return the task
     * @throws DukeException if there is an error parsing the input to a LocalDate
     */
    public static Task fromFileDescription(String input) throws DukeException {
        return null;
    }


    /**
     * Returns a boolean denoting if the event is happening at the queried date.
     *
     * @param localDate the queried date
     * @return false as there is no date specified
     */
    public boolean isHappeningOnDate(LocalDate localDate) {
        return false;
    }
    
    /**
     * Returns a boolean to check if keyword specified by user is in the task's description.
     *
     * @param keyword the keyword entered by the user
     * @return true if and only if the keyword is contained in the description
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
