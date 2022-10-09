package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.Date;
import duke.DukeException;

/**
 * This class encapsulates a deadline set by the user.
 */
public class Deadline extends Task {
    public static final char SYMBOL = 'D';

    private LocalDate date;

    /**
     * Creates a Deadline with the given description and date.
     *
     * @param description The description for the Deadline.
     * @param date        The date for the Deadline.
     * @throws DukeException If the description is empty, or if the format of the given date is wrong.
     */
    public Deadline(String description, String date) throws DukeException {
        super(description, false);
        try {
            this.date = Date.getDate(date);
        } catch (DateTimeException e) {
            throw new DukeException("That's not a proper date.");
        }
    }

    /**
     * Creates a Deadline with the given description, completion status, and date.
     *
     * @param description The description for the Deadline.
     * @param isDone      The completion status of the Deadline.
     * @param date        The date for the Deadline.
     * @throws DukeException If the description is empty, or if the format of the given date is wrong.
     */
    public Deadline(String description, boolean isDone, String date) throws DukeException {
        super(description, isDone);
        try {
            this.date = Date.getDate(date);
        } catch (DateTimeException e) {
            throw new DukeException("That's not a proper date.");
        }
    }

    /**
     * Returns the date of this Deadline.
     *
     * @return The date of this Deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns a Deadline after decoding the given String.
     *
     * @param s The String to decode.
     * @return The Deadline decoded from the String.
     * @throws DukeException If the String is empty, or if the format of the date in the String is wrong.
     */
    public static Deadline decode(String s) throws DukeException {
        // Solution below adapted from https://github.com/teikjun/duke
        String[] arguments = s.split(";");
        boolean isDone = arguments[0].equals("1");
        return new Deadline(arguments[1], isDone, arguments[2]);
    }

    /**
     * Encodes this Deadline into a String.
     *
     * @return The String encoded from this Deadline.
     */
    @Override
    public String encode() {
        return String.format("%c;%s;%s",
                SYMBOL,
                super.encode(),
                Date.getStorageFormat(this.date));
    }

    /**
     * Returns the String representation of this Deadline.
     *
     * @return A String representing this Deadline.
     */
    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)",
                SYMBOL,
                super.toString(),
                Date.getDisplayFormat(this.date));
    }

    /**
     * Compares this Deadline with the given Task.
     * If the given Task is a Deadline or an Event, their dates are compared chronologically.
     * Otherwise, this Deadline is always smaller.
     *
     * @param task The Task to compare to.
     * @return A negative integer, zero, or a positive integer as this Deadline is
     *     smaller than, equals to, or greater than the given Task.
     */
    @Override
    public int compareTo(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return this.date.compareTo(deadline.date);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return this.date.compareTo(event.getDate());
        } else {
            return -1;
        }
    }
}
