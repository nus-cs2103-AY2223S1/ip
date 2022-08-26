package piggy.task;

/**
 * A Task made for deadlines that can store a deadline.
 */
public class Deadline extends TaskWithDate {

    /**
     * Creates a new Deadline with the given description and deadline.
     * The Deadline is marked as not done by default.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description, by);
    }

    /**
     * Returns a String representation of the Deadline.
     *
     * @return A String of the format "[D][<X or ' '>] <description> (by: <deadline>)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.getDateTime() + ")";
    }
}

