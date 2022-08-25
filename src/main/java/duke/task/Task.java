package duke.task;

/**
 * Task class that contains types of tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of task and sets task to be not done
     * @param description initialises string description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task with X if done and " " if not done
     * @return a status icon of "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string of the task
     * @return
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}
