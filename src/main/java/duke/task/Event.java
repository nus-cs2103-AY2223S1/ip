package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

/**
 * Represents an event task in the Duke application.
 * Event is a Task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /** Exception due to wrong event format. */
    public static final DukeException WRONG_FORMAT =
            new DukeException("Wrong format for Event!\nShould be 'event <description> /at YYYY-MM-DD'.");

    /** Date of the event. */
    private final LocalDate date;

    /**
     * Constructor for an event that takes in description and date.
     * Event is set as "not done" when created.
     *
     * @param description Description of an event.
     * @param date        Date of an event.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Factory method for an Event that takes in done, description and date.
     *
     * @param done        Whether the Event is done.
     * @param description Description of Event.
     * @param date        Date of Event.
     * @return Event object with the given parameters.
     */
    public static Event create(String done, String description, String date) {
        Event event = new Event(description, LocalDate.parse(date));
        if (done.equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Gets the Event in a format for file saving.
     *
     * @return Event in file saving format.
     */
    public String getFileFormat() {
        return String.format("E | %s | %s", super.getFileFormat(), this.date);
    }

    /**
     * Gets the string representation of an Event.
     *
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        String date = this.date.format(formatter);
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
