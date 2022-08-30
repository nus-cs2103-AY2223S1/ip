package data;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private final String title;
    private boolean isDone;

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}
