package duke.task;

public abstract class Task {

    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String encodeToString();
}