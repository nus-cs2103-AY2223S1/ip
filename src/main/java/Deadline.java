/**
 * Class handling the deadline task type.
 */
public class Deadline extends Task {
    /* Time frame that the Deadline object should be completed by*/
    protected String by;

    /**
     * Constructor for Deadline Class.
     *
     * @param name String representation of task name.
     * @param by   String representation of task deadline.
     */
    public Deadline(String name, String by) {
        this.name = name;
        this.isDone = false;
        this.by = by;
    }

    /**
     * Returns string representation of Deadline object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns formatted string representation of deadline task for save processing.
     *
     * @return Formatted string representation of deadline task.
     */
    @Override
    public String saveFormat() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s", "D", status, name, by);
    }
}
