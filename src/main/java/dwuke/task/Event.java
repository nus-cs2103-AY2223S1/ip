package dwuke.task;

import dwuke.Date;
import dwuke.DwukeException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * This class encapsulates an event set by the user.
 */
public class Event extends Task {
    public static final char SYMBOL = 'E';
    private LocalDate date;

    /**
     * Creates an Event with the given description and date.
     *
     * @param description The description for the Event.
     * @param date The date for the Event.
     * @throws DwukeException If the description is empty, or if the format of the given date is wrong.
     */
    public Event(String description, String date) throws DwukeException {
        super(description, false);
        try {
            this.date = Date.parse(date);
        } catch (DateTimeException e) {
            throw new DwukeException("dats not a pwopew date");
        }
    }

    /**
     * Creates an Event with the given description, completion status, and date.
     *
     * @param description The description for the Event.
     * @param isDone The completion status of the Event.
     * @param date The date for the Event.
     * @throws DwukeException If the description is empty, or if the format of the given date is wrong.
     */
    public Event(String description, boolean isDone, String date) throws DwukeException {
        super(description, isDone);
        try {
            this.date = Date.parse(date);
        } catch (DateTimeException e) {
            throw new DwukeException("dats not a pwopew date");
        }
    }

    /**
     * Returns an Event after decoding the String.
     *
     * @param s The String to decode.
     * @return The Event decoded from the String.
     * @throws DwukeException If the String is empty, or if the format of the date in the String is wrong.
     */
    public static Event decode(String s) throws DwukeException {
        String[] parts = s.split(";");
        boolean isDone = parts[0].equals("1");
        return new Event(parts[1], isDone, parts[2]);
    }

    /**
     * Encodes this Event into a String.
     *
     * @return The String encoded from this Event.
     */
    @Override
    public String encode() {
        return SYMBOL + ";" + super.encode() + ";" + Date.storageFormat(this.date);
    }

    /**
     * Returns the String representation of this Event.
     *
     * @return A String representing this Event.
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + Date.displayFormat(this.date) + ")";
    }
}

