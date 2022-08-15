/**
 * A task that needs to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String text, String by) {
        super(text);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
