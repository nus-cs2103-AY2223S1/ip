package duke;

/**
 * Encapsulates a tasks inputted by the user.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     * @param description The description of the Task object.
     * @param isDone Boolean value of the status of Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getFileIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Changes the status of task to be done.
     */
    public void mark() {
        this.isDone = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n    %s\n", this));
    }

    /**
     * Changes the status of the task to be undone.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println(String.format("Ok, I've marked this task as not done yet:\n    %s\n", this));
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("%s", this.description);
    }

    public abstract String toFileString();
}
