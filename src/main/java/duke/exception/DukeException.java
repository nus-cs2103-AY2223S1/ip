package duke.exception;

/**
 * Exception representing invalid commands fed to application.
 *
 * @author WR3nd3
 */
public class DukeException extends RuntimeException {


    /**
     * Constructs duke.exception.DukeException object with custom error message.
     *
     * @param message String representing error message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }



}
