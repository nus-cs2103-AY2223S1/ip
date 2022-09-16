package bro.task;

/**
 * Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the Task class.
     * @param description Description to be given to the variable description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns 'X' if done or " " otherwise.
     * @return String "X" if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

