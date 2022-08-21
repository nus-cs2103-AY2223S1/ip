package dobby.tasks;

import dobby.commands.*;
import dobby.*;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void mark() {
        isDone = true;
    }
    public void unmark() {
        isDone = false;
    }
    public boolean isDone() {
        return isDone;
    }
    public String toPrint() {
        return " | " + "[" + getStatusIcon() + "] | " + description;
    }
    public boolean isPresent(String toFind) {
        boolean isPresent = description.contains(toFind);
        return isPresent;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
