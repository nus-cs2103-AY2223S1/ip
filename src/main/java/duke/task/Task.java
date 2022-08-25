package duke.task;

public class Task {
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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Getter for the description of the duke.task.Task.
     * @return The description of the duke.task.Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for isDone.
     * @return If this task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
