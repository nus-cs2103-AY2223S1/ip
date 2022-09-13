package Duke.tasks;

import java.util.Arrays;
import java.util.List;

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

    public void mark() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public List<String> toList() {
        return Arrays.asList(description, getStatusIcon());
    }

    public void unmark() {
        this.isDone = false;
    }

    public String fileFormat() {
        return String.format("todo | %s | %b", description, isDone);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
