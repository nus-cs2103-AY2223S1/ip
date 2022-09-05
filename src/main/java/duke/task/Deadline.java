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
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy MMM dd hh:mma");

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

        //check the class invariant
        assert hasValidDateTime() : "Construction failed - Invalid LocalDateTime.";
    }

    /**
     * Constructor for Deadline given completeness of task.
     *
     * @param description Description of the Deadline.
     * @param isDone Completeness of Deadline.
     * @param by The due date of the Deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;

        //check the class invariant
        assert hasValidDateTime() : "Construction failed - Invalid LocalDateTime.";
    }

    /**
     * Returns validity of by.
     *
     * @return true if valid by, false otherwise.
     */
    private boolean hasValidDateTime() {
        return this.by != null;
    }

    /**
     * Returns the Date and Time of Deadline.
     *
     * @return Date and Time of Deadline.
     */
    public String printDateTime() {
        String dueDate = by.format(DATE_TIME_FORMATTER);
        return dueDate;
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
