package Duke;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "\u2714" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void UnmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "]" + " " + this.description;
    }
}
