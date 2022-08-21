package dwuke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

import dwuke.Date;
import dwuke.DwukeException;

/**
 * This class encapsulates a deadline set by the user.
 */
public class Deadline extends Task {
    public static final char SYMBOL = 'D';
    private LocalDate date;

    /**
     * Creates a new Deadline with the given description and date.
     *
     * @param description The description for the Deadline.
     * @param date        The date for the Deadline.
     * @throws DwukeException If the description is empty, or if the format of the given date is wrong.
     */
    public Deadline(String description, String date) throws DwukeException {
        super(description, false);
        try {
            this.date = Date.parse(date);
        } catch (DateTimeException e) {
            throw new DwukeException("dats not a pwopew date");
        }
    }

    Deadline(String description, boolean isDone, String date) throws DwukeException {
        super(description, isDone);
        try {
            this.date = Date.parse(date);
        } catch (DateTimeException e) {
            throw new DwukeException("dats not a pwopew date");
        }
    }

    /**
     * Returns a Deadline after decoding the String.
     *
     * @param s The String to decode.
     * @return The Deadline decoded from the String.
     * @throws DwukeException If the String is empty, or if the format of the date in the String is wrong.
     */
    public static Deadline decode(String s) throws DwukeException {
        String[] parts = s.split(";");
        boolean isDone = parts[0].equals("1");
        return new Deadline(parts[1], isDone, parts[2]);
    }

    /**
     * Encodes the Deadline into a String.
     *
     * @return The Deadline encoded as String.
     */
    @Override
    public String encode() {
        return SYMBOL + ";" + super.encode() + ";" + Date.storageFormat(this.date);
    }

    /**
     * Returns the String representation of this deadline.
     *
     * @return A String representing this deadline.
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + Date.displayFormat(this.date) + ")";
    }
}
