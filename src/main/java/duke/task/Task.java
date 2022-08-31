package duke.task;

/**
 *  Task class.
 */
public class Task {

    /** Stores the description of a task. */
    private String description;

    /** Boolean value to indicate whether a task is done or not. */
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task based on status of the task.
     *
     * @return Returns [X] + description if the task is done or
     * [ ] if the not done.
     */
    public String getDescription() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * The string representation of task.
     *
     * @return Returns [X] + description if the task is done or
     * [ ] if the not done.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

}