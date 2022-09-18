package bobby.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task
     * @param description task description
     */
    public Task(String description) {
        assert(!description.isEmpty());
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for task
     * @param description task description
     * @param isDone task status
     */
    public Task(String description, Boolean isDone) {
        assert(!description.isEmpty());
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status of task
     * @return status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Toggles status of task
     */
    public void toggleStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public abstract String formatTaskString();


}
