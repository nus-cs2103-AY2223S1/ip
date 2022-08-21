import java.time.LocalDate;

/**
 * This class encapsulates a deadline set by the user.
 */
public class Deadline extends Task {
    public static final char SYMBOL = 'D';
    private LocalDate date;

    Deadline(String description, String date) throws DwukeException {
        super(description, false);
        this.date = Date.parse(date);
    }

    Deadline(String description, boolean isDone, String date) throws DwukeException {
        super(description, isDone);
        this.date = Date.parse(date);
    }

    /**
     * Returns a Deadline after decoding the String.
     *
     * @param s The String to decode.
     * @return The Deadline decoded from the String.
     * @throws DwukeException If the String is empty.
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
