package main.java.duke;

public class Task {
    protected String action;
    protected boolean isDone;

    public Task(String action) {
        this.action = action;
        this.isDone = false;
    }

    public void markIsDone() {
        this.isDone = true;
    }

    public void unmarkIsDone() {
        this.isDone = false;
    }

    public String getAction() { return this.action; }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.action;
    }

}
