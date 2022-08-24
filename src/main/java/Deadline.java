package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline encapsulates a task with a deadline date/time.
 *
 * @author Totsuka Tomofumi
 * @version Level-6
 */
public class Deadline extends Task {
    /**
     * Deadline date/time.
     */
    private LocalDate time;

    /**
     * Constructor for this deadline.
     * @param description Description of deadline
     * @param time Time of deadline
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the deadline.
     * @return deadline status and its description
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
