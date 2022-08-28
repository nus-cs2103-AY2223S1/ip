package duke.task;

import java.util.ArrayList;
import java.util.Arrays;

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
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean containsKeyword(String keyword) {
        ArrayList<String> descriptionDelimited = new ArrayList<String>(Arrays.asList(this.description.split(" ")));
        for (String word : descriptionDelimited) {
            if (keyword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    abstract String storedTaskString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}