package dwuke.task;

import dwuke.DwukeException;

/**
 * This class encapsulates a to-do set by the user.
 */
public class Todo extends Task {
    public static final char SYMBOL = 'T';

    /**
     * Creates a To-do with the given description.
     *
     * @param description The description for the To-do.
     * @throws DwukeException If the description is empty.
     */
    public Todo(String description) throws DwukeException {
        super(description);
    }

    /**
     * Creates a To-do with the given description and completion status.
     *
     * @param description The description for the To-do.
     * @param isDone      The completion status of the To-do.
     * @throws DwukeException If the description is empty.
     */
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
     * Encodes this To-do into a String.
     *
     * @return The String encoded from this To-do.
     */
    @Override
    public String encode() {
        return SYMBOL + ";" + super.encode();
    }

    /**
     * Returns the String representation of this To-do.
     *
     * @return A String representing this To-do.
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }
}
