package duke;

/**
 * A class that creates a Task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor that initialises the Task object.
     *
     * @param description Describes the activity of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return The string that indicates if the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the task as not done.
     */
    public void unMarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task and the status of it.
     *
     * @return String that describes the activity and the status of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
    //...
}
