public class Task {
    protected String description;
    protected String tag;
    protected boolean isDone;

    public Task(String description, String tag) {
        this.description = description;
        this.tag = tag;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + tag + "][" + getStatusIcon() + "] " + getDescription();
    }
}
