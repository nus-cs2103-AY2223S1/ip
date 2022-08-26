package duke.task;

import java.io.Serializable;

/**
 * Encapsulates a Task
 */
public abstract class Task implements Serializable {
    private String name;
    private boolean isComplete;
    private char type;

    /**
     * Constructor for a Task
     *
     * @param name Name of the task
     * @param type Type of the task
     * @param isComplete Status of current task, true if task is completed false otherwise
     */
    public Task(String name, char type, boolean isComplete) {
        this.name = name;
        this.type = type;
        this.isComplete = isComplete;
    }

    /**
     * Constructor for a Task
     *
     * @param name Name of the task
     * @param type Type of the task
     */
    public Task(String name, char type) {
        this(name, type, false);
    }

    /**
     * Updates the status of the task to be completed
     */
    public void markComplete() {
        this.isComplete = true;
    }

    /**
     * Updates the status of the task to be incomplete
     */
    public void markIncomplete() {
        this.isComplete = false;
    }

    /**
     * Returns string representation of the task consisting of the type of the task, the completion
     * status of the task and the task description
     * */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s", type, isComplete ? "X" : " ", name);
    }
}
