package duke.exception;
/**
 * DukeNoMatchException Class.
 * Exception when there are no matched task for find Command.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeNoMatchException extends DukeException {
    /**
     * Constructor for DukeNoKeywordException.
     */
    public DukeNoMatchException() {
        super("If you wanna find it, put it in they said .-. I cant find matching ones.");
    }
}
