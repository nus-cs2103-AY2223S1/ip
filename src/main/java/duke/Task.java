package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toStorageString() {
        int value;
        if (isDone) {
            value = 1;
        } else {
            value = 0;
        }
        return " | " + value + " | " + this.description;
    }

    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]: " + this.description;
    }
}
