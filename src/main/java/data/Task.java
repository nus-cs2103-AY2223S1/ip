package data;

import java.io.Serializable;

/**
 * Abstract task that has a title and a boolean of whether it is done.
 */
public abstract class Task implements Serializable {
    private final String title;
    private boolean isDone;

    /**
     * Constructs a new task with a title and whether it is done.
     *
     * @param title  Title of task.
     * @param isDone Whether task is done.
     */
    public Task(String title, boolean isDone) {
        assert !title.isEmpty();
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

    public boolean contains(String substring) {
        return this.title.contains(substring);
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}
