package duke;

public abstract class Task {
    private final String name;
    private boolean isComplete;

    /**
     * Constructs a new Task object.
     * @param name the name of the task
     * @param isInitiallyComplete whether the task is complete at the time of creation
     */
    public Task(String name, boolean isInitiallyComplete) {
        this.name = name;
        this.isComplete = isInitiallyComplete;
    }

    /**
     * Returns the name of the task.
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the task is complete.
     * @return whether the task is complete
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Sets the task as complete.
     */
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    /**
     * Returns a string representation of the task.
     * @return a string representation of the task
     */
    public String toString() {
        String checkbox = this.isComplete ? "[X]" : "[ ]";
        return checkbox + " " + this.name;
    }

    public abstract String toFileRepresentation();
}