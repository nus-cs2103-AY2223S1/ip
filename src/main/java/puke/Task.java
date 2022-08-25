package puke;

/**
 * Task - can take 3 forms , Deadline , Event or ToDo
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task.
     * @param description what the task is
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Queries the status of the task
     * @return String that represents if the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Get string representation of task
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Gets the format to save task on hard disk
     * @return String representation of task save format
     */

    public abstract String saveFormat();

}
