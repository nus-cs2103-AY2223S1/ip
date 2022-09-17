package duke;
/**
 * Represents a task that can be created by the user.
 */
public class Task {
    private String taskName;
    private boolean isComplete;
    /**
     * Constructor used to create a new Task object
     * @param s Command containing task message and time
     * @param isComplete whether the task is completed
     */
    public Task (String s, boolean isComplete) {
        this.taskName = s;
        this.isComplete = isComplete;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean status) {
        isComplete = status;
    }

    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "- | " + completion + " | " + this.getTaskName();
    }

}
