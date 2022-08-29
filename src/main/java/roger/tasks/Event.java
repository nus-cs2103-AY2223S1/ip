package roger.tasks;

import java.time.LocalDate;


/**
 * Encapsulates an event.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Create an event.
     *
     * @param name The name of the event.
     * @param date The date of the event.
     */
    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public boolean isOnDate(LocalDate date) {
        return this.date.equals(date);
    }

    /**
     * String representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.toString() + ")";
    }

    /**
     * Returns the date of the event.
     *
     * @return The date of the event.
     */
    public LocalDate getDate() {
        return this.date;
    }
}
