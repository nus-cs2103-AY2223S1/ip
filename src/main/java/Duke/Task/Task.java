package Duke.Task;

abstract public class Task {
    protected String description;
    protected String commandString;
    protected boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return (isDone? "mark" : "unmark");
    }

    public void done() {
        this.isDone = true;
    }

    public void undone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toStorageString() {
        return commandString + "\n" + getStatus();
    }
}
