package duke.task;

import duke.exception.TaskMarkException;
import duke.exception.TaskUnmarkException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() throws TaskMarkException{
        if (isDone) {
            throw new TaskMarkException();
        }
        isDone = true;
    }

    public void unmark() throws TaskUnmarkException{
        if (!isDone) {
            throw new TaskUnmarkException();
        }
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
