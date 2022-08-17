/**
 * An exception that is specific to Duke.
 *
 * @author Lai Han Wen
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of a DukeException.
     *
     * @return the String representation of a DukeException.
     */
    @Override
    public String toString() {
        return getMessage() + "\nEnter command:";
    }

}
