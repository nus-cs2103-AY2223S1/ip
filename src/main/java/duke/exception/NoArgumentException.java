package duke.exception;

import duke.command.Action;

/**
 * The DukeException that the user does not enter arguments to a Duke Action which needs arguments.
 */
public class NoArgumentException extends CompileException {
    /**
     * Constructs NoArgumentException.
     * @param action The invoking Action.
     */
    public NoArgumentException(Action action) {
        super("The description of a [" + Action.convertToString(action) + "] cannot be empty."
                + "\nThe format of [" + Action.convertToString(action) + "] should be '"
                + Action.getFormat(action) + "'");
    }
}
