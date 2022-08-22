package duke.task;

import duke.DukeException;

/**
 * This class encapsulates a to-do set by the user.
 */
public class Todo extends Task {
    public static final char SYMBOL = 'T';

    /**
     * Creates a To-do with the given description.
     *
     * @param description The description for the To-do.
     * @throws DukeException If the description is empty.
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Creates a To-do with the given description and completion status.
     *
     * @param description The description for the To-do.
     * @param isDone      The completion status of the To-do.
     * @throws DukeException If the description is empty.
     */
    Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
    }

    /**
     * Returns a To-do after decoding the String.
     *
     * @param s The String to decode.
     * @return The To-do decoded from the String.
     * @throws DukeException If the String is empty.
     */
    public static Todo decode(String s) throws DukeException {
        String[] arguments = s.split(";");
        boolean isDone = arguments[0].equals("1");
        return new Todo(arguments[1], isDone);
    }

    /**
     * Encodes this To-do into a String.
     *
     * @return The String encoded from this To-do.
     */
    @Override
    public String encode() {
        String s = String.format("%c;%s",
                SYMBOL,
                super.encode());
        return s;
    }

    /**
     * Returns the String representation of this To-do.
     *
     * @return A String representing this To-do.
     */
    @Override
    public String toString() {
        String s = String.format("[%c]%s",
                SYMBOL,
                super.toString());
        return s;
    }
}
