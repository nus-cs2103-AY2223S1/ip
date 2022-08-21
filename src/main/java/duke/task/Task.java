package duke.task;

/**
 * Task class that can be inherited from different types of tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task
     *
     * @param description what the task contains
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks done task with X
     *
     * @return String that contains a mark if the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of task
     *
     * @return string that is representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Sets task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}
