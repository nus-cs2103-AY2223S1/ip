import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * One of the task types, specifically those with
 * specified timing of when the task should be done by.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor method.
     *
     * @param description The event description
     * @param by The deadline of this task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * The stringtified version of this deadline.
     *
     * @return The print format of this deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}