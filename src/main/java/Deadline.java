/**
 * ToDo class that stores the Description and State of Deadline
 * CS2103T IP
 * AY22/23 Semester 1
 *
 * @author Tan Jia Rong
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline
     * @param description Description of the Deadline task
     * @param by The Deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * To string method of Deadline
     *
     * @return String Representation of a Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}