package duke.task;

public abstract class Task {
    private final String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void isDoneSetter(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean containKeyword(String keyword) {
        return this.taskDescription.contains(keyword);
    }

    protected abstract String getTypeIcon();

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    public String toStorageString() {
        return (isDone ? "1" : "0") + "|" + taskDescription;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + taskDescription;
    }
}
