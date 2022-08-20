/**
 * Deadline class that stores the Description and State of Deadline.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Deadline extends Task {
    /** Stores the due date of the Deadline. */
    protected String by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline task.
     * @param by The due date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline.
     * @param done Completeness of Deadline.
     * @param by The due date of the Deadline.
     */
    public Deadline(String description, String done, String by) {
        super(description, done,"E");
        this.by = by;
    }

    /**
     * Gets due date of Deadline.
     *
     * @return Due date of Deadline.
     */
    public String getDate() {
        return this.by;
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + super.toString()  + " (by: " + by + ")";
    }
}