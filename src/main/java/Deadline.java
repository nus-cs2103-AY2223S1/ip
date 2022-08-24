public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline class
     * @param description Description of deadline
     * @param by Due timing/date of deadline
     */
    public Deadline(String description, Boolean isDone, String by) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
