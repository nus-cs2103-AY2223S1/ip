/**
 * Handles a task with a deadline.
 */
public class Deadline extends Task {
    protected ParsedDateTime datetime;

    /**
     * Creates a Deadline object.
     * @param description Description of deadline.
     * @param by Time of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        datetime = new ParsedDateTime(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetime.toString());
    }
}
