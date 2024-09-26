package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a date and time.
 */
public class Event extends Task {
    private LocalDateTime date;

    /**
     * Constructor method for an Event.
     *
     * @param description description of the event
     * @param dateStr date and time of the event
     */
    public Event(String description, String dateStr) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.date = LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateStr() + ")";
    }

    /**
     * Returns the date and time of the event.
     *
     * @return the date and time of the event
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Returns the string representation of the date and time of the event.
     *
     * @return string representation of the date and time of the event
     */
    public String getDateStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, hh:mma dd MMMM yyyy");
        return this.date.format(formatter);
    }

    /**
     * Returns the string representation of the Event to be saved.
     *
     * @return string representation of the Event to be saved
     */
    @Override
    public String save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateStr = this.date.format(formatter);
        return "E" + super.save() + " | " + dateStr;
    }

    /**
     * Tests whether the event is on the given date.
     *
     * @param dateStr the given date
     * @return true if the deadline is on the given date, false otherwise
     */
    public boolean isOnDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate other = this.date.toLocalDate();
        return date.equals(other);
    }

    public boolean isAfter(LocalDateTime other) {
        return this.date.isAfter(other);
    }
}
