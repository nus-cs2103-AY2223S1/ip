public class Task {

    enum Tag {
        T,
        E,
        D
    }

    protected String description;
    protected Tag tag;
    protected boolean isDone;

    public Task(String description, Tag tag) {
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
