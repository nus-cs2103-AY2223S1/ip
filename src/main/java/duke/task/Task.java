package duke.task;

public class Task {
    enum Tag {
        Todo,
        Event,
        Deadline
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

    public String getTag() {
        switch(tag) {
        case Todo:
            return "T";
        case Event:
            return "E";
        case Deadline:
            return "D";
        default:
            return " ";
        }
    }

    public String toString() {
        return "[" + getTag() + "][" + getStatusIcon() + "] " + getDescription();
    }
}
