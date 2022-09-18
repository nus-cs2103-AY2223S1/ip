package stashy.data.task;

import java.time.LocalDateTime;

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
     * Represents the constructor method.
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
     * Converts the Deadline object into a string.
     *
     * @return The stringtified version with the deadline in "MMM dd yyyy HHmm" format
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDateTime.format(OUTPUT_DATETIME_PATTERN) + ")";
    }
}
