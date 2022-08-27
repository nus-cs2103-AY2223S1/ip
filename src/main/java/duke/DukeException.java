/**
 * This class handles all exceptions specific to duke.
 */
package duke;

public class DukeException extends Exception {

    public static final String UNRECOGNISED_COMMAND = "i don't know what that means!";
    public static final String OUT_OF_RANGE = "there's nothing there :<";
    public static final String MISSING_INDEX = "pls enter an index!";
    public static final String MISSING_DESCRIPTION = "description cannot be empty!!";
    public static final String MISSING_DATE= "date cannot be empty!";
    public static final String WRONG_FORMAT = "wrong format!";
    public static final String WRONG_FORMAT_DATE = "wrong date format! pls re-enter using yyyy-mm-dd";

    /**
     * Constructor for the DukeException class.
     * @param message Message of the exception thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}