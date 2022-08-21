/**
 * This class encapsulates an event set by the user.
 */
public class Event extends Task {
    private String date;

    Event(String str) throws DwukeException {
        super(str);
        String[] arguments = getArguments(str);
        this.changeDescription(arguments[0]);
        this.date = arguments[1];
    }

    Event(String description, boolean isDone, String date) throws DwukeException {
        super(description, isDone);
        this.date = date;
    }

    private static String[] getArguments(String str) throws DwukeException {
        int index = str.indexOf("/at");

        if (index == -1) throw new DwukeException("'/at' not fwound");

        try {
            String description = str.substring(0, index - 1);
            String date = str.substring(index + 4);
            return new String[]{description, date};
        } catch (StringIndexOutOfBoundsException e) {
            throw new DwukeException("da descwiption and date cannot be empty");
        }
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
        return "E;" + super.encode() + ";" + this.date;
    }

    /**
     * Returns the String representation of this event.
     *
     * @return A String representing this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}

