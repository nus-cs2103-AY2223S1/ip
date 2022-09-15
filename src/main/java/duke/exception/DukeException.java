package duke.exception;

/**
 * The Exceptions might be thrown and caught by Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * The constructor of the Duke Exception.
     * @param message
     */
    public DukeException(String message) {
        super("OOPS!!! " + message);
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
        if (obj instanceof DukeException) {
            DukeException e = (DukeException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
