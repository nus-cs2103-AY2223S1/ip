package duke;

/**
 * Implements the Exception that is thrown
 * in the Duke program.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.2
 */
public class DukeException extends Exception{

    /**
     * Creates an instance of a DukeException object.
     *
     * @param message The message that is contained by the DukeException object.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Gives the String representation of the DukeException Object.
     *
     * @return String The String format of the DukeException Object.
     */
    @Override
    public String toString() {
        return "DukeException detected.";
    }
}
