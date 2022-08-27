package duke.exception;

import duke.command.Action;
import duke.util.Parser;

/**
 * The DukeException that the user enters incorrect arguments to a Duke Action.
 */
public class InvalidArgumentException extends CompileException {
    /**
     * The constructor of the Exception.
     * @param action The invoking Action.
     * @param message The error information.
     */
    public InvalidArgumentException(Action action, String message) {
        super("The description of a [" + Action.getString(action) + "] is incorrect.\n"
                + message
                + "\nThe format of [" + Action.getString(action) + "] should be '" + Action.getFormat(action) + "'"
                + "\nAttribute Separator: '" + Parser.getAttributeSeparator() + "', is not allowed.");
    }

    /**
     * Return boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InvalidArgumentException) {
            InvalidArgumentException e = (InvalidArgumentException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
