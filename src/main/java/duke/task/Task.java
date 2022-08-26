package duke.task;

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

    public void finished() {
        isDone = true;
    }

    public void notFinished() {
        isDone = false;
    }

    public abstract String textFormat();

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[" + getStatusIcon() + "] ");
        str.append(description);
        return str.toString();
    }
}
