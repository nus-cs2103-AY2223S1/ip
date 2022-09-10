package drake.tasks;

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

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public List<String> toList() {
        return Arrays.asList(description, getStatusIcon());
    }

    public boolean isMatch(List<String> searchKeywords) {
        return searchKeywords.stream().allMatch(description::contains);
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
