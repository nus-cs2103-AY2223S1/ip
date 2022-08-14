package task;

/**
 * The Deadline class encapsulates a type of task to be done.
 * A Deadline task is a task that needs to be done before a specific time/date.
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", "D", super.toString(), this.by);
    }
}
