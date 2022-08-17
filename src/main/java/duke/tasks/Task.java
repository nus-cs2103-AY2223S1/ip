package duke.tasks;

public abstract class Task {

    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        isDone = false;
    }

    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String savedString();
}