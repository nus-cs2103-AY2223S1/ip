/**
 * DukeExceptions represent errors in user input when using Alfred.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException.
     *
     * @param message Error Message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of this DukeException.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        return " ☹ Apologies! " + super.getMessage() + "\n";
    }
}
