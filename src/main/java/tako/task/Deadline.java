package tako.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for a deadline.
     *
     * @param description Description of deadline.
     * @param by Date and time to finish by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
