package duke.exception;

import duke.command.Action;

public class NoArgumentException extends CompileException {
    public NoArgumentException(Action action) {
        super("The description of a [" + Action.getString(action) + "] cannot be empty."
                + "\nThe format of [" + Action.getString(action)+ "] should be '" + Action.getFormat(action)+ "'");
    }
}
