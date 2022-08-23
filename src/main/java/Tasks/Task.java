package Tasks;

import dukeExceptions.IllegalUseException;

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

    public abstract String getEncodedValue();

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean completedStatus) {
        this.isComplete = completedStatus;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isComplete ? "X" : " ", this.taskDescription);
    }
}
