package duke.exception;

import duke.command.Action;

public class NoArgumentException extends CompileException {
    public NoArgumentException(Action action) {
        super("The description of a [" + Action.getString(action) + "] cannot be empty."
                + "\nThe format of [" + Action.getString(action) + "] should be '" + Action.getFormat(action) + "'");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NoArgumentException) {
            NoArgumentException e = (NoArgumentException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
