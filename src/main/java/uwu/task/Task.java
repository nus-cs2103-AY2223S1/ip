package uwu.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String task) {
        this.description = task;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]\t" + description;
    }

    public String toStorageString() {
        String isDoneIndicator = isDone ? "1" : "0";
        return "Task," + isDoneIndicator + "," + description;
    }
}
