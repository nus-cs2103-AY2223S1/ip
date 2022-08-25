package duke.exception;

/**
 * The DukeException when user enters an invalid action keyword.
 */
public class InvalidActionException extends CompileException {
    /**
     * The constructor of the class with the incorrect Action name.
     * @param action The incorrect Action name.
     */
    public InvalidActionException(String action) {
        super("I don't know what [" + action + "] means :-(");
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
        if (obj instanceof InvalidActionException) {
            InvalidActionException e = (InvalidActionException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
