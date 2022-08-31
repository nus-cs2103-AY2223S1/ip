package duke;

/**
 * A class used to represent a task. A task has a name and completion status.
 */
public abstract class Task {
    protected static final String MARK_CHARACTER = "X";

    protected String taskName = "";
    protected boolean isDone = false;

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public abstract String toSaveFormatString();

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? MARK_CHARACTER : " ", this.taskName);
    }
}
