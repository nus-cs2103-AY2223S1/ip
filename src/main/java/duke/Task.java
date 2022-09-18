package duke;

public class Task {
    private final String name;
    private boolean isDone;
    private Tag tag;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.name;
    }

    public void markTaskAsDone() {
        this.isDone = true;
    }

    public void markTaskAsUndone() {
        this.isDone = false;
    }

    public void addTag(Tag tag) {
        this.tag = tag;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTagString() {
        return tag != null ? "Tag: " + tag.toString() : "";
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskName();
    }
}
