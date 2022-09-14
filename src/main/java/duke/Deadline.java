package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adds on with a by field which allows users to set a deadline.
 *
 * @author Yuvaraj Kumaresan
 */
public class Deadline extends Task {

    /**
     * Time at which the deadline is due.
     */
    protected LocalDateTime by;

    /**
     * Constructor
     *
     * @param description String describing the deadline task.
     * @param by          String providing the timeframe for the deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts deadline object to its string representation.
     *
     * @return String representation of the deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm")) + ")";
    }

    /**
     * Gets the by attribute from the deadline object.
     *
     * @return String representation of the by attribute.
     */
    public LocalDateTime getBy() {
        return this.by;
    }
}
