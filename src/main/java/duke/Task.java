package duke;

/**
 * Encapsulates a Task stored in Duke.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean contains(String s) {
        return description.contains(s);
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns deadline task into a savable format.
     *
     * @return A string representing the data stored in the task.
     */
    public String parseToSaveData() {
        int isDoneNum = isDone ? 1 : 0;
        return isDoneNum + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
