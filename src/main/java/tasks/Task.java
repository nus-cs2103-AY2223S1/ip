package tasks;

/**
 * Tasks.Task represents a generic task that subclasses can inherit from. Represents
 * something that needs to be done.
 * <p>
 * This class should not be used on its own, but inherited by other subclasses and
 * used there.
 */
public abstract class Task {
    private final String taskDescription;
    private boolean isComplete = false; // Initialized to false by default

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Abstract function to be overridden representing a Task's internal representation
     * in storage.
     *
     * @return String representing internal representation of a Task.
     */
    public abstract String getEncodedValue();

    /**
     * Returns a Task's description.
     *
     * @return String representing a Tasks's description.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns whether a Task is completed.
     *
     * @return Boolean representing if a Task is complete.
     */
    public boolean getIsComplete() {
        return this.isComplete;
    }

    /**
     * Sets the completion state of a Task.
     *
     * @param completedStatus Boolean representing if the Task is complete.
     */
    public void setIsComplete(boolean completedStatus) {
        this.isComplete = completedStatus;
    }

    /**
     * Returns string representation of the Task. Should be overridden in child
     * Task classes.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isComplete ? "X" : " ", this.taskDescription);
    }
}
