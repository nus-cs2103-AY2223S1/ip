public class Deadline extends Task{
    /**
     * Deadline object by field which indicates the deadline
     */
    protected String by;

    /**
     * A constructor to intialize the Deadline object with the description and deadline
     *
     * @param description The task
     * @param by The deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }
}

