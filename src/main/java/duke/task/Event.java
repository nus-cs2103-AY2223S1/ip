package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.Date;
import duke.DukeException;


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
     * @param date        The date for the Event.
     * @throws DukeException If the description is empty, or if the format of the given date is wrong.
     */
    public Event(String description, String date) throws DukeException {
        super(description, false);
        try {
            this.date = Date.parse(date);
        } catch (DateTimeException e) {
            throw new DukeException("That's not a proper date.");
        }
    }

    /**
     * Creates an Event with the given description, completion status, and date.
     *
     * @param description The description for the Event.
     * @param isDone      The completion status of the Event.
     * @param date        The date for the Event.
     * @throws DukeException If the description is empty, or if the format of the given date is wrong.
     */
    public Event(String description, boolean isDone, String date) throws DukeException {
        super(description, isDone);
        try {
            this.date = Date.parse(date);
        } catch (DateTimeException e) {
            throw new DukeException("That's not a proper date.");
        }
    }

    /**
     * Returns an Event after decoding the String.
     *
     * @param s The String to decode.
     * @return The Event decoded from the String.
     * @throws DukeException If the String is empty, or if the format of the date in the String is wrong.
     */
    public static Event decode(String s) throws DukeException {
        String[] arguments = s.split(";");
        boolean isDone = arguments[0].equals("1");
        return new Event(arguments[1], isDone, arguments[2]);
    }

    /**
     * Encodes this Event into a String.
     *
     * @return The String encoded from this Event.
     */
    @Override
    public String encode() {
        String s = String.format("%c;%s;%s",
                SYMBOL,
                super.encode(),
                Date.storageFormat(this.date));
        return s;
    }

    /**
     * Returns the String representation of this Event.
     *
     * @return A String representing this Event.
     */
    @Override
    public String toString() {
        String s = String.format("[%c]%s (at: %s)",
                SYMBOL,
                super.toString(),
                Date.displayFormat(this.date));
        return s;
    }
}

