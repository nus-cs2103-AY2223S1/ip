package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task that happens at a particular time.
 */
public class Event extends Task {
    private final LocalDate dateAt;

    /**
     * Creates an event.
     *
     * @param title  Title of event.
     * @param done   If it is done.
     * @param dateAt Date of event.
     */
    public Event(String title, boolean done, LocalDate dateAt) {
        super(title, done);
        assert !title.isEmpty();
        assert dateAt != null;
        this.dateAt = dateAt;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + dateAt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
