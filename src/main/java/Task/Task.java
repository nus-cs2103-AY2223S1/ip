package Task;

import java.io.Serializable;

public abstract class Task implements Serializable {

    private final String task;

    private boolean isDone = false;

    /**
     * Initialises a task object.
     *
     * @param task description of the task
     */
    public Task(String task) {
        this.task = task;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the tasl
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return the completion status of the task.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Marks the task as complete.
     *
     * @return the String representation of the marking action.
     */
    public String markDone() {
        this.isDone = true;
        return "Yatta~ Congrats master, you've completed this task!\n" + this;
    }

     public boolean contains(String keyword) {
         return task.contains(keyword);
     }

    /**
     * Marks the task as incomplete.
     *
     * @return the String representation of the unmarking action.
     */
    public String unmarkDone() {
        this.isDone = false;
        return "All the best for this task, master!\n" + this;
    }
}