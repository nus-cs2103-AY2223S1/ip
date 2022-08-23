public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the Deadline task.
     * @param by Date the Deadline task is due by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Shows the Deadline task description and the date it is due by.
     *
     * @return String with the Deadline task description and date it is due by.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
