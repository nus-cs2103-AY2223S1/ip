package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Child class duke.Deadline
 *
 * duke.Deadline a child class of duke.Task has the same functionality
 * but adds on with a by field which allows users to set a deadline.
 *
 * @author Yuvaraj Kumaresan
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor
     *  @param description String describing the deadline task.
     * @param by          String providing the timeframe for the deadline task.*/
    public Deadline(String description, LocalDateTime by) {
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
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm")) + ")";
    }

    /**
     * Method getBy()
     *
     * @return String representation of the by attribute.
     */
    public LocalDateTime getBy() {
        return this.by;
    }
}
