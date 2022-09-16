package duke;

/**
 * Encapsulates a tasks inputted by the user.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected PriorityLevel.Priority level;


    /**
     * Creates a Task object.
     * @param description The description of the Task object.
     * @param isDone Boolean value of the status of Task.
     */
    public Task(String description, boolean isDone, PriorityLevel.Priority level) {
        this.description = description;
        this.isDone = isDone;
        this.level = level;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getFileIcon() {
        return (isDone ? "1" : "0");
    }

    public int getPriorityInteger() {
        return this.level.ordinal();
    }
    public String getPriorityLevel() {
        return this.level.toString();
    }


    public String changeTaskPriority(PriorityLevel.Priority level) {
        this.level = level;
        return String.format("Got it! I've set this task as priority level %s", level.toString());
    }

    /**
     * Changes the status of task to be done.
     */
    public String mark() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n    %s\n", this);
    }

    /**
     * Changes the status of the task to be undone.
     */
    public String unmark() {
        this.isDone = false;
        return String.format("Ok, I've marked this task as not done yet:\n    %s\n", this);
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
