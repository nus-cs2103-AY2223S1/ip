package ip.task;

public abstract class Task {
    /** Description of the task. */
    protected String description;
    /** Completion status of the task. */
    protected boolean isComplete;

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

    public abstract String writeFormat();
    public abstract boolean hasString(String s);

    @Override
    public String toString() {
        return check() + " " + description;
    }
}
