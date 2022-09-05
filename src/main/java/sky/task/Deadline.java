package sky.task;

/**
 * The Deadline class encapsulates a Deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline with provided description and date by.
     *
     * @param description Description of the task to be done.
     * @param by Date and optionally time, in the format of yyyy/mm/dd XXXX, where XXXX is time in 24-hours.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public Deadline makeACopy() {
        Deadline copiedTask = new Deadline(super.description, this.by);
        return copiedTask;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
