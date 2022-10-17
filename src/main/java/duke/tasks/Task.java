package duke.tasks;

/**
 * Abstract class that represents tasks to be stored on Duke.
 */
public abstract class Task {
    /** Description of Task */
    protected String description;
    /** Status of Task, whether it is done or not */
    protected boolean isDone;

    /**
     * Constructs a Task with specified description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns string representation of current task status.
     *
     * @return String representation of task status.
     */
    public String getStatusIcon() {
        String done = "X";
        String notDone = " ";
        return (isDone ? done : notDone);
    }

    /**
     * Returns if task is already completed.
     *
     * @return Task completion status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Updates the task with a new description.
     *
     * @param newDescription New description to be updated.
     */
    public void updateDescription(String newDescription) {
        String emptyString = "";
        if (!newDescription.equals(emptyString)) {
            description = newDescription;
        }
    }

    /**
     * Generates data string representation of task to be stored in storage.
     *
     * @return Data string representation of task.
     */
    public abstract String taskToDataString();

    /**
     * Generates data string representation of isDone boolean as an icon.
     *
     * @return Data string representation of isDone.
     */
    public String isDoneToDataString() {
        String isDoneIcon;
        if (this.isDone) {
            isDoneIcon = "O";
        } else {
            isDoneIcon = "X";
        }
        return isDoneIcon;
    }

    /**
     * Returns true if keyword was found in current task, else false.
     *
     * @param keyword Keyword to search for.
     * @return If keyword was found in current task.
     */
    public Boolean isFoundInDescription(String keyword) {
        // Checks if keyword is found in description in case-insensitive manner.
        return (description.toLowerCase()).contains(keyword.toLowerCase());
    }

    /**
     * Returns string representation of task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String taskStringFormat = "[%s] %s";
        return String.format(taskStringFormat, this.getStatusIcon(), description);
    }
}
