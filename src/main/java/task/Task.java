package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task.
     * @param description Full description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Returns an X if the task when activity is done.
     * @return X if task is done, else blank.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Unmarks the Task by setting isDone to false.
     */
    public void unMark() {
        this.isDone = false;
    }


    /**
     * Marks the Task by setting isDone to True.
     */
    public void mark() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "  + description;
    }
}
