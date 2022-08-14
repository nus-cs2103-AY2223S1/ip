/**
 * Deadline class has a by field
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status;
        if (this.isDone) {
            status = String.format("[D][✓] %s", this.description);
        } else {
            status = String.format("[D][ ] %s (by: %d)", this.description, by);
        }
        return status;
    }
}