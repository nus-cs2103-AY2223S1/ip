package duke.exception;

import duke.command.Action;

/**
 * The DukeException that the user does not enter arguments to a Duke Action which needs arguments.
 */
public class NoArgumentException extends CompileException {
    /**
     * The constructor of the Exception.
     * @param action The invoking Action.
     */
    public NoArgumentException(Action action) {
        super("The description of a [" + Action.convertToString(action) + "] cannot be empty."
                + "\nThe format of [" + Action.convertToString(action) + "] should be '"
                + Action.getFormat(action) + "'");
    }

    /**
     * Returns boolean indicating whether this object
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
