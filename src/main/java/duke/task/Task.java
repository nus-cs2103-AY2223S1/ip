package duke.task;

import duke.DukeException;

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

    public abstract boolean isOnThisDate(String dateStr) throws DukeException;

    public abstract boolean doesDescriptionContain(String input) throws DukeException;

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
