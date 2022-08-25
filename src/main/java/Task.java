public abstract class Task {
    protected String taskName;
    protected Boolean isDone;

    public Task(ParsedData parsedData) {
        this.taskName = parsedData.getTaskName();
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public abstract String getTypeIcon();

    @Override
    public String toString() {
        return taskName;
    }
}
