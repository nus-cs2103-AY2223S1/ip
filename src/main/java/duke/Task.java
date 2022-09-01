package duke;

/**
 * The Task class is an abstract class that has a description and an indicator depicting whether
 * it has been done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object with a given description and sets the isDone indicator as false.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon depicting whether the Task has been done.
     *
     * @return 'X' if Task is done, ' ' otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the Task object.
     *
     * @return Description of the Task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of the Task to 'done'.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets the status of the Task to 'not done'.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the task type of the Task object.
     *
     * @return The task type of the Task object.
     */
    public abstract String getTaskType();

    /**
     * Returns the String representation of the Task object.
     *
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Formats the details of the Task object such that the information can be saved and loaded
     * from files.
     *
     * @return The String representation of the Task object.
     */
    String saveStringToFile() {
        return String.format("%s\n%s\n%d\n", getTaskType(), description, isDone ? 1 : 0);
    }
}


