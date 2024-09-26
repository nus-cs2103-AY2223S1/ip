package duke;

import java.util.ArrayList;

/**
 * Task contains the description of the Task to be done,
 * as well as a boolean that tracks the completion of the Task.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.2
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an instance of a Task object.
     *
     * @param description The description of the Task that needs to be completed.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gives the String representation of the Task Object.
     *
     * @return String The String format of the Task Object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    /**
     * Marks whether the Task has been completed.
     *
     * @return String that corresponds to whether the Task has been done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Adds a task to a given taskList input. This method
     * should be overridden by all child classes.
     *
     * @param taskList The taskList before a Task is added.
     * @return ArrayList of type Task The taskList after the Task is added.
     */
    public abstract String printAndStoreTask(ArrayList<Task> taskList);

    /**
     * Checks if there is a clash in dates between the tasks.
     *
     * @param dateTime dateTime of the given Task.
     * @return boolean Whether there is a clash.
     */
    public abstract boolean sameTime(String dateTime);

    /**
     * Toggles the status of the task to Done.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Toggles the status of the task to Not Done.
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Checks if a given keyword matches the Task description.
     *
     * @param keyword keyword to check against.
     * @return Whether there match between the given keyword and the description.
     */
    public boolean match(String keyword) {
        return this.description.contains(keyword);
    }
}
