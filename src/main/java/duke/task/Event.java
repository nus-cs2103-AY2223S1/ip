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
            this.date = Date.getDate(date);
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
            this.date = Date.getDate(date);
        } catch (DateTimeException e) {
            throw new DukeException("That's not a proper date.");
        }
    }

    /**
     * Returns the date of this Event.
     *
     * @return The date of this Event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns an Event after decoding the String.
     *
     * @param s The String to decode.
     * @return The Event decoded from the String.
     * @throws DukeException If the String is empty, or if the format of the date in the String is wrong.
     */
    public static Event decode(String s) throws DukeException {
        // Solution below adapted from https://github.com/teikjun/duke
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
        return String.format("%c;%s;%s",
                SYMBOL,
                super.encode(),
                Date.getStorageFormat(this.date));
    }

    /**
     * Returns the String representation of this Event.
     *
     * @return A String representing this Event.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s)",
                SYMBOL,
                super.toString(),
                Date.getDisplayFormat(this.date));
    }

    /**
     * Compares this Event with the given Task.
     * If the given Task is an Event or a Deadline, their dates are compared chronologically.
     * Otherwise, this Event is always smaller.
     *
     * @param task The Task to compare to.
     * @return A negative integer, zero, or a positive integer as this Event is
     *     smaller than, equals to, or greater than the given Task.
     */
    @Override
    public int compareTo(Task task) {
        if (task instanceof Event) {
            Event event = (Event) task;
            return this.date.compareTo(event.date);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return this.date.compareTo(deadline.getDate());
        } else {
            return -1;
        }
    }
}

