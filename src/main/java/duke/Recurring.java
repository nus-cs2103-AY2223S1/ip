package duke;

/**
 * Represents an recurring task.
 */
public class Recurring extends Task {

    protected String interval;

    /**
     * Creates a new recurring task with the given description and repeating time interval.
     *
     * @param description Description of the task.
     * @param interval    Repeating time interval in any format.
     */
    public Recurring(String description, String interval) {
        super(description);
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "[R]" + super.toString() + " (repeats: " + interval + ")";
    }
}
