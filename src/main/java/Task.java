/**
 * The Task class models a task. A task has a description and an indicator depicting whether it has
 * been done.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new Task object with a given description and whether it has been done.
     *
     * @param description the description of the task
     * @param isDone whether the task is marked as done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether the given Task object is marked as done.
     *
     * @return whether the task is done
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the Task object
     *
     * @return description of the Task object
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets whether the Task is done.
     *
     * @param isDone whether the Task is done
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the status icon depicting whether the Task has been done.
     *
     * @return 'X' if Task is done, ' ' otherwise
     */
    //@@author cheeheng-reused
    //Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
    //with minor modifications
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status of the Task to 'done'.
     */
    public void markAsDone() {
        this.setIsDone(true);
    }

    /**
     * Sets the status of the Task to 'not done'.
     */
    public void markAsUndone() {
        this.setIsDone(false);
    }

    /**
     * Returns the task type code of the Task object. The task type code will be displayed on the
     * command line interface.
     *
     * @return the task type code of the Task object
     */
    public abstract String getTaskTypeCode();

    /**
     * Returns the String representation of the Task object, i.e.
     *   a string in the format "[task type code][get status icon] description".
     *
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskTypeCode(), this.getStatusIcon(),
                this.description);
    }
}
