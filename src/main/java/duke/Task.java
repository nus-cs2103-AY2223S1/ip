package duke;

import java.util.ArrayList;

/**
 * Task contains the description of the Task to be done,
 * as well as a boolean that tracks the completion of the Task.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor that creates an instance of a Task object.
     *
     * @param description The description of the Task that needs to be completed.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The toString method for the Task class.
     *
     * @return String The String format of the Task Object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    /**
     * A method to mark whether the Task has been completed.
     *
     * @return String that corresponds to whether the Task has been done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * A method to add a task to a given taskList input. This method
     * should be overridden by all child classes.
     *
     * @param taskList The taskList before a Task is added.
     * @return ArrayList of type Task The taskList after the Task is added.
     */
    public String printAndStoreTask(ArrayList<Task> taskList) {
        return null;
    }

    /**
     * A method to toggle the status of the task to Done.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * A method to toggle the status of the task to Not Done.
     */
    public void undoTask() {
        this.isDone = false;
    }

    public boolean match(String keyword) {
        return this.description.contains(keyword);
    }
}
