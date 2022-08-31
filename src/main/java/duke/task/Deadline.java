package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that stores the Description and State of Deadline.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Deadline extends Task {
    /** Stores the due date of the Deadline. */
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline.
     * @param by The due date of the Deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline.
     * @param isDone Completeness of Deadline.
     * @param by The due date of the Deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the Date and Time of Deadline.
     *
     * @return Date and Time of Deadline.
     */
    public String printDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd hh:mma");
        return by.format(formatter);
    }

    /**
     * Stringify deadline for storage.
     *
     * @return a string representing the deadline.
     */
    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "D", super.stringify(), by);
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + printDateTime() + ")";
    }
}
