package duke.exception;

import duke.Parser;
import duke.command.Action;

public class InvalidArgumentException extends CompileException {
    public InvalidArgumentException(Action action, String message) {
        super("The description of a [" + Action.getString(action) + "] is incorrect.\n"
                + message
                + "\nThe format of [" + Action.getString(action)+ "] should be '" + Action.getFormat(action) + "'"
                + "\nAttribute Separator: '" + Parser.getAttributeSeparator() + "', is not allowed.");
    }
}
