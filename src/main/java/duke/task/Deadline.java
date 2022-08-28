package duke.task;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /** Deadline of the task */
    private final ZonedDateTime by;

    /**
     * Creates a new undone task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, ZonedDateTime by) {
        super(description);
        this.by = by;
    }

    public ZonedDateTime getBy() {
        return by;
    }

    /**
     * {@inheritDoc}
     *
     * @return 'D'
     */
    @Override
    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma"))
        );
    }
}
