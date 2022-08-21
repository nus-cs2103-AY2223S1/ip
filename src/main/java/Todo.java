/**
 * This class encapsulates a to-do set by the user.
 */
public class Todo extends Task {
    Todo(String description) throws DwukeException {
        super(description);
    }

    Todo(String description, boolean isDone) throws DwukeException {
        super(description, isDone);
    }

    /**
     * Returns a To-do after decoding the String.
     *
     * @param s The String to decode.
     * @return The To-do decoded from the String.
     * @throws DwukeException If the String is empty.
     */
    public static Todo decode(String s) throws DwukeException {
        String[] parts = s.split(";");
        boolean isDone = parts[0].equals("1");
        return new Todo(parts[1], isDone);
    }

    /**
     * Encodes the To-do into a String.
     *
     * @return The To-do encoded as String.
     */
    @Override
    public String encode() {
        return "T;" + super.encode();
    }

    /**
     * Returns the String representation of this to-do.
     *
     * @return A String representing this to-do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
