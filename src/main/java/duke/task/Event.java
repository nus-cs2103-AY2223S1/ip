package duke.task;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Datetime of the event */
    private final ZonedDateTime at;

    /**
     * Creates a new undone event with the specified description and datetime.
     *
     * @param description The description of the event.
     * @param at The datetime of the event.
     */
    public Event(String description, ZonedDateTime at) {
        super(description);
        this.at = at;
    }

    public ZonedDateTime getAt() {
        return at;
    }

    /**
     *
     * {@inheritDoc}
     *
     * @return 'E'
     */
    @Override
    public char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma"))
        );
    }
}
