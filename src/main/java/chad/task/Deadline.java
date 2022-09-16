package chad.task;

import java.time.LocalDateTime;

/**
 * Deadline task that inherits from task
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor for Deadline
     *
     * @param description description of deadline
     * @param dateTime date and time of when deadline is due
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;

    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.dateTime.format(OUTPUT_DATEFORMAT) + ")";
    }
}
