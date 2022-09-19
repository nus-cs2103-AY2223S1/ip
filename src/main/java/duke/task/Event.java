package duke.task;

import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/** This class encapsulates a Event task that occurs at a date */
public class Event extends Task {

    /** Event date of the task */
    private String date;

    /**
     * Initialises Event object.
     * @param description
     * @param at
     * @param dateIsFormatted True if date has already been formatted.
     * @throws DukeException
     */
    public Event(String description, String at, boolean dateIsFormatted) throws DukeException {
        super(description);
        if (dateIsFormatted == true) {
            this.date = at;
        } else {
            this.date = getLocalDateTime(at, "event", "/at").format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Returns String representation of the event details.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

}
