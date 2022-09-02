package stashy.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * One of the task types, specifically those with
 * specified timing of when the task should be done by.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task as a LocalDateTime object.
     */
    protected LocalDateTime byDateTime;

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param byDateTime The deadline of this task
     * @param isDone Whether the task is done or not
     */
    public Deadline(String description, LocalDateTime byDateTime, boolean isDone) {
        super(description, isDone);
        this.byDateTime = byDateTime;
    }

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param byDateTime The deadline of this task
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        this(description, byDateTime, false);
    }

    /**
     * Converts the Deadline object into a string.
     *
     * @return The stringtified version with the deadline in "MMM dd yyyy HHmm" format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
