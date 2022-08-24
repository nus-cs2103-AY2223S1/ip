/**
 * Class for the details of a task that Poolsheen can remember.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */

public abstract class Task {
    /** The details of the task */
    protected String description;

    /** If the task is finished */
    protected boolean isDone;

    /** The time for the task */
    protected String time;

    /**
     * Returns the status of the task.
     * @return A string of either "X" or "-".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "-"); // mark done task with X
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}