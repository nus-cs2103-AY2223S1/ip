package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public abstract String taskToDataString();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
