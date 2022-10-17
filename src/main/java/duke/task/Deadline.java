package duke.task;

/**
 * The Deadline class represents a Task that has to be done by a given date.
 *
 * @author Edric Yeo
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline instance with a given date.
     *
     * @param description The description of the Deadline.
     * @param by          The date that the Deadline has to be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataEntry() {
        int mark = this.isDone ? 1 : 0;
        return String.format("D # %d # %s # %s\n", mark, this.description, this.by);
    }
}
