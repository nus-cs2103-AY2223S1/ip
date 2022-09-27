package duke.exception;

/**
 * DukeNoKeywordException Class.
 * Exception when there is no input for find Command.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeNoKeywordException extends DukeException {
    /**
     * Constructor for DukeNoKeywordException.
     */
    public DukeNoKeywordException() {
        super("Please input a keyword for me to search for .-.");
    }
}
