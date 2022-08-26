package ip.task;

public class Task {
    /** Description of the task. */
    private String description;
    /** Completion status of the task. */
    private boolean isComplete;

    public Task() {
        isComplete = false;
    }
    /**
     * Sets task description to the given string.
     * 
     * @param s The given string description of the task.
     */
    public void describe(String s) {
        this.description = s;
    }

    public void mark() {
        isComplete = true;
    }

    public void unmark() {
        isComplete = false;
    }

    private String check() {
        return isComplete ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return check() + " " + description;
    }
}
