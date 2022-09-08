package roger.tasks;

import java.time.LocalDate;


/**
 * Encapsulates an event.
 */
public class Event extends Task {

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

    /**
     * String representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.toString() + ")";
    }
}
