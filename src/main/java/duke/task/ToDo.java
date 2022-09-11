package duke.task;

/**
 * A to do task is a task that does not have any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a to-do task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param completion Whether the to-do task has been completed.
     */
    public ToDo(String description, boolean completion) {
        super(description, completion);
    }

    @Override
    public ToDo edit(String userEditInput) {
        description = userEditInput;
        return this;
    }

    /**
     * Parses the to-do into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the To-Do Task.
     */
    @Override
    public String toSaveFormat() {
        String completionStatus = this.isDone ? "Y" : "N";
        // escape instances of deliminator in task description
        String escapedDescription = this.description.replace("|", "\\|");
        return String.format("T | %s | %s", completionStatus, escapedDescription);
    }

    /**
     * Returns a string representation for the to-do task, prefixed with a [T],
     * followed by the task status, and the task description.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
