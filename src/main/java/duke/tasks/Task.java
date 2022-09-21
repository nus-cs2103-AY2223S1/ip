package duke.tasks;

/**
 * Event implements methods for Task objects.
 *
 * @author Isaac Li Haoyang
 * @version v0.2
 */
public abstract class Task {

    private final String taskDesc;
    private boolean isCompleted;

    /**
     * Creates a new Task object.
     *
     * @param taskDesc description of the task
     */
    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isCompleted = false;
    }

    /**
     * Fetches the description of the task.
     *
     * @return the description of the task
     */
    public String getDesc() {
        return this.taskDesc;
    }

    /**
     * Checks if the task has been marked as completed.
     *
     * @return True if the task is completed
     */
    public boolean isisCompleted() {
        return this.isCompleted;
    }

    /**
     * Fetches the type of task for identification during encoding and decoding for storage.
     *
     * @return a Character representing the type of task
     */
    public abstract char getTaskType();

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    public String toString() {
        if (isCompleted) {
            return "[X] " + taskDesc;
        } else {
            return "[ ] " + taskDesc;
        }
    }
}
