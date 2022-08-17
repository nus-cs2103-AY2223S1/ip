/**
 * A task to be completed by a certain time.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Deadline extends Task {

    /**
     * Time that the task needs to be completed by.
     */
    protected String by;

    /**
     * A basic constructor to instantiate the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Method that returns the description of the Deadline.
     *
     * @return The description of the Event along with its status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
