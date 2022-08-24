package main.java;

/**
 * Deadline encapsulates a task with a deadline date/time.
 *
 * @author Totsuka Tomofumi
 */
public class Deadline extends Task {
    /**
     * Deadline date/time.
     */
    private String time;

    /**
     * Constructor for this deadline.
     * @param description Description of deadline
     * @param time Time of deadline
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the deadline.
     * @return deadline status and its description
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.time);
    }

    @Override
    public String toData() {
        return "D" + super.toData() + this.time;
    }
}
