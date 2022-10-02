package cwq.task;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Task class encapsulate the behavior of a Duke task
 */
public class Task implements Serializable {
    private final String taskDescription;

    private boolean isMarked;

    /**
     * Constructor for Task class
     * @param taskDescription the content of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isMarked = false;
    }

    /**
     * Change the status of a task
     */
    public void changeStatus(boolean newStatus) {
        this.isMarked = newStatus;
    }

    @Override
    public String toString() {
        if (isMarked) {
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

    public LocalDateTime getTime() {
        return null;
    }

}
