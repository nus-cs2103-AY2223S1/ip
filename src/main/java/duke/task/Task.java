package duke.task;

/**
 * Class to represent a task.
 */
public class Task {
    /**
     * Description of the task.
     */
    protected String description;
    /**
     * Status of whether task is completed.
     */
    protected boolean isDone;
    /**
     * String representing type of task.
     */
    protected String type = "";

    /**
     * Constructor for a new task.
     *
     * @param description Description of new task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets type of task.
     *
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets description of task.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Changes status of task to done.
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * Changes status of task to undone.
     */
    public void taskUndone() {
        this.isDone = false;
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
