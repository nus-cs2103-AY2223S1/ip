package data;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private final String title;
    private boolean isDone;

    /**
     * Constructs a new task with a title and whether it is done.
     * @param title Title of task.
     * @param isDone Whether task is done.
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Mark task as done.
     */
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
