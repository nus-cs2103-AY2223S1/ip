package duke.task;

/**
 * A to do task is a task that does not have any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructor for a to-do task.
     *
     * @param description Description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, boolean completion) { super(description, completion); }

    /**
     * Parses the to-do into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the To-Do Task
     */
    @Override
    public String toSaveFormat() {
        return String.format("T | %s | %s", this.isDone ? "Y" : "N",
                this.description.replace("|", "\\|"));
    }

    /**
     * Returns a string representation for the to-do task, prefixed with a [T],
     * followed by the task status, and the task description.
     *
     * @return The string representation of the to-do task
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
