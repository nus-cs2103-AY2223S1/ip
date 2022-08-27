package duke;

/**
 * Represents tasks to be done,
 * Parent class for subclasses Deadline,
 * Events and ToDos
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public String toString() {
        return this.getStatusIcon() + this.description;

    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

}