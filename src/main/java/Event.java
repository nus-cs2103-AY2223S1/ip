import java.time.LocalDate;

/**
 * This class encapsulates an event set by the user.
 */
public class Event extends Task {
    public static final char SYMBOL = 'E';
    private LocalDate date;

    Event(String description, String date) throws DwukeException {
        super(description, false);
        this.date = Date.parse(date);
    }

    Event(String description, boolean isDone, String date) throws DwukeException {
        super(description, isDone);
        this.date = Date.parse(date);
    }

    /**
     * Returns an Event after decoding the String.
     *
     * @param s The String to decode.
     * @return The Event decoded from the String.
     * @throws DwukeException If the String is empty.
     */
    public static Event decode(String s) throws DwukeException {
        String[] parts = s.split(";");
        boolean isDone = parts[0].equals("1");
        return new Event(parts[1], isDone, parts[2]);
    }

    /**
     * Encodes the Event into a String.
     *
     * @return The Event encoded as String.
     */
    @Override
    public String encode() {
        return SYMBOL + ";" + super.encode() + ";" + Date.storageFormat(this.date);
    }

    /**
     * Returns the String representation of this event.
     *
     * @return A String representing this event.
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + Date.displayFormat(this.date) + ")";
    }
}

