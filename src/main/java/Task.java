public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        type = 'T';
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + type + "][" + getStatusIcon() + "]" + description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }
}
