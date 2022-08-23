package duke.task;

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
        isDone = true;
    }

    public void unMark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] " + description, getStatusIcon());
    }
}
