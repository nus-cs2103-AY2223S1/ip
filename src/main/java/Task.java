public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Getters for task
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String getDescription() {
        return description;
    }
    // Setters for task
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
