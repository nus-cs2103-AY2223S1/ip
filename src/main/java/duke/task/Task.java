package duke.task;

/**
 * All types of tasks
 */
public class Task {
    private String description;
    private boolean isDone;
    String tag;

    Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "no tag";
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getOutput() {
        return String.format("O | %d | %s", isDone ? 1 : 0, description);
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public void addTag(String tag) {
        this.tag = tag;
    }

    public void loadTag(String tag) {
        this.tag = tag;
    }
}
