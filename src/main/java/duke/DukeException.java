package duke;

/**
 * DukeException implements the Exception that is thrown
 * in the Duke program.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class DukeException extends Exception{

    /**
     * DukeException constructor that creates an instance of a DukeException object.
     *
     * @param message The message that is contained by the DukeException object.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * The toString method for the DukeException class.
     *
     * @return String The String format of the DukeException Object.
     */
    @Override
    public String toString() {
        return "DukeException detected.";
    }
}
