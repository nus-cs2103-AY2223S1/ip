package duke.task;

/**
 * Task to be done and inserted into the Duke program.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Task {

    protected String description;

    protected boolean isDone;

    /**
     * Instantiates a task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the task description with its mark status.
     *
     * @return Description of Task.
     */
    public String toString() {
        String markStatus = "[" + getStatusIcon() + "] ";
        return markStatus + this.description;
    }
}
