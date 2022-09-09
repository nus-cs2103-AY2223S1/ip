package doemon.task;

import java.util.ArrayList;

public class Task {

    /** Description of the task. */
    private String description;

    /** Boolean indicating if the task is marked as done. */
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string used to save the task.
     *
     * @return A string used to save the task.
     */
    public String saveString() {
        String isMarked = isDone ? "1" : "0";
        return String.format("%s | %s", isMarked, this.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
