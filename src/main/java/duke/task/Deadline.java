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
            this.date = Date.parse(date);
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
            this.date = Date.parse(date);
        } catch (DateTimeException e) {
            throw new DukeException("That's not a proper date.");
        }
    }

    /**
     * Returns a Deadline after decoding the given String.
     *
     * @param s The String to decode.
     * @return The Deadline decoded from the String.
     * @throws DukeException If the String is empty, or if the format of the date in the String is wrong.
     */
    public static Deadline decode(String s) throws DukeException {
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
        String s = String.format("%c;%s;%s",
                SYMBOL,
                super.encode(),
                Date.storageFormat(this.date));
        return s;
    }

    /**
     * Returns the String representation of this Deadline.
     *
     * @return A String representing this Deadline.
     */
    @Override
    public String toString() {
        String s = String.format("[%c]%s (by: %s)",
                SYMBOL,
                super.toString(),
                Date.displayFormat(this.date));
        return s;
    }
}
