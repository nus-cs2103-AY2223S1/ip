package duke;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}