package main.java;

public class Task {
    protected String action;
    protected boolean isDone;

    public Task(String action) {
        this.action = action;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return this.action;
    }

}
