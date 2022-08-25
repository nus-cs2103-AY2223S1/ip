package duke.exception;

/**
 * The DukeException regarding the executing of Duke Commands.
 */
public class DukeRuntimeException extends DukeException {
    /**
     * The constructor of DukeRuntimeException with given information.
     * @param msg The given information.
     */
    public DukeRuntimeException(String msg) {
        super(" I'm sorry that an error occurs when executing your command, "
                + "please check with following information:\n" + msg);
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
        if (obj instanceof DukeRuntimeException) {
            DukeRuntimeException e = (DukeRuntimeException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
