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
