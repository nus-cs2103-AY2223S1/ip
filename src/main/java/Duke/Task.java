package duke;

/**
 * Task object class.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description Details of the Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done with a tick or undone with space.
     *
     * @return String tick if task is done else an empty space.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    /**
     * Gets the details of the task.
     *
     * @return String of task details.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Representation of Task object.
     *
     * @return String format of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
