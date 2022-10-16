package seedu.duke;

/**
 * Represents excpetions related to the Duke program.
 */
public class DukeException extends Exception {

    /**
     * A constructor for Duke Exception.
     *
     * @param message the message to be displayed when a Duke Exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + super.getMessage();
    }


}
