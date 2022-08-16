public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true; // mark tasks as done
    }

    public void markAsUndone() {
        this.isDone = false; // mark task as undone
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
