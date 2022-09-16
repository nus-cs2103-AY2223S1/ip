package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, a task at a specified time.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Initialises new event with description and date.
     *
     * @param description Description of Event.
     * @param at Date of event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns whether Event is on that date.
     *
     * @param date Specified date.
     * @return Boolean of whether Event is on that date.
     */
    public boolean isOn(LocalDate date) {
        return this.at.equals(date);
    }

    /**
     * Returns file representation of Event.
     *
     * @return "E | {1 if done else 0} | {description} | {at}"
     */
    @Override
    public String toFileRepresentation() {
        return String.format("E | %s | %s", super.toFileRepresentation(), this.at);
    }

    /**
     * Returns new Event instance from file representation.
     *
     * @param rep String representation of Event.
     * @return New Event instance.
     */
    public static Event fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        String date = args[3];
        Event result = new Event(description, LocalDate.parse(date));
        if (isDone) {
            result.markDone();
        }
        return result;
    }

    /**
     * Returns String representation of Event.
     *
     * @return "[E] | {[X] if done else [ ]} | {description} | {formattedAt}"
     */
    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)",
                super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("E, d MMM yyyy")));
    }
}
