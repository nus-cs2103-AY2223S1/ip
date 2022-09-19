package duke;

/**
 * DukeException class throws a checked exception when exceptions are thrown and caught during
 * the execution of the Duke program
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }


}
