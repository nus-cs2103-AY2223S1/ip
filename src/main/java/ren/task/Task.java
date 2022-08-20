package ren.task;

/**
 * Parent Class for all Tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the Task information for writing to a File.
     *
     * @return String with Task information.
     */
    public abstract String writeData();

    /**
     * Sets the completion status of this task.
     *
     * @param isDone New completion status of the task.
     * @return String with message for user.
     */
    public String setDone(boolean isDone) {
        this.isDone = isDone;
        return isDone
            ? " Great job! I will mark the task as completed.\n" + "   " + this
            : " Understood. I will mark the task as uncompleted.\n" + "   " + this;
    }
}
