package duke.task;

import java.time.LocalDateTime;

/**
 * A Deadline object that is a type of Task, but contains the date and time on top of the task description.
 */
public class Deadline extends Task{
    
    /** Datetime of the deadline */
    protected LocalDateTime by;

    /**
     * Creates a Deadline object.
     *
     * @param description the description of this
     * @param by the deadline of this
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    
    /**
     * Returns the LocalDateTime of this Deadline instance.
     *
     * @return the datetime information of this {@code Deadline}
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DATE_TIME_FORMATTER) + ")";
    }
}
