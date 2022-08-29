package duke.task;

import java.io.Serializable;

/**
 * Task class encapsulate the behavior of a Duke task
 */
public class Task implements Serializable {
    private final String taskDescription;

    private boolean taskStatus;

    /**
     * Constructor for Task class
     * @param taskDescription the content of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskStatus = false;
    }

    /**
     * Change the status of a task
     */
    public void changeStatus(boolean newStatus) {
        this.taskStatus = newStatus;
    }

    /**
     * Returns the string representation of a Task.
     * @return a string
     */
    @Override
    public String toString() {
        if (taskStatus) {
            return "[X] " + taskDescription;
        } else {
            return "[] " + taskDescription;
        }
    }

    /**
     * Accessor for the taskDescription field.
     * @return the taskDescription
     */
    public String getTaskDescription() {
        return taskDescription;
    }

}
