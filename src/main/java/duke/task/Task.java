package duke.task;

/**
 * Simulates the individual task entered by the user and
 * encapsulates the task description as well as task status
 * within its fields.
 */
public class Task {
    //To encapsulate the information regarding the task
    protected String description;
    //To keep track of the status of the task (done/not done)
    protected boolean isDone;

    /**
     * Constructor to create an instance of Task.
     *
     * @param description String describing the task to be done.
     * @param isDone Boolean to keep track if the task has been marked before
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks the status of the task (done/undone) and mark it with an X if it is done.
     *
     * @return String representation of "X" for tasks that are done and an empty space
     *     " " for tasks that are undone.
    */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the field isDone for each task as done (true).
     */
    public void markTaskDone() {
        isDone = true;
    }

    /**
     * Mark the field isDone for each task as undone (false).
     */
    public void markTaskUndone() {
        isDone = false;
    }

    /**
     * Provides a string representation of the task status and task
     * description as follows "[task status] task_description".
     *
     * @return String representation of the formatted task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
