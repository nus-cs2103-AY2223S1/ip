package duke.exception;

import duke.command.Action;
import duke.util.Parser;

/**
 * The DukeException that the user enters incorrect arguments to a Duke Action.
 */
public class InvalidArgumentException extends CompileException {
    /**
     * Constructs InvalidArgumentException.
     * @param action The invoking Action.
     * @param message The error information.
     */
    public InvalidArgumentException(Action action, String message) {
        super("The description of a [" + Action.convertToString(action) + "] is incorrect.\n"
                + message
                + "\nThe format of [" + Action.convertToString(action) + "] should be '"
                + Action.getFormat(action) + "'"
                + "\nAttribute Separator: '" + Parser.getAttributeSeparator() + "', is not allowed.");
    }
}
