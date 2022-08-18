/**
 * Child class Deadline
 *
 * Deadline a child class of Task has the same functionality
 * but adds on with a by field which allows users to set a deadline.
 *
 * @author Yuvaraj Kumaresan
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor
     *
     * @param description String describing the deadline task.
     * @param by          String providing the timeframe for the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Method toString()
     *
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
