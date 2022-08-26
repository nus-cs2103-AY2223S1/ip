package duke.task;

/**
 * Represents a Task. A Task object contains the description and completion of task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     * @param description String describing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     *
     * @return String describing completion of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as finished
     */
    public void finished() {
        isDone = true;
    }

    /**
     * Marks task as not finished
     */
    public void notFinished() {
        isDone = false;
    }

    /**
     *
     * @return String to save into text file
     */
    public abstract String textFormat();

    /**
     *
     * @return String to describe task to users
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[" + getStatusIcon() + "] ");
        str.append(description);
        return str.toString();
    }
}
