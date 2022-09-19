package duke.exceptions;

/**
 * Exception thrown when the same asynchronous command is called while another one is still running
 */
public class OperatonIsStillRunningException extends DukeException {
    private static final String DESCRIPTION = "I am still doing the previous task! Wait!";

    public OperatonIsStillRunningException() {
        super(DESCRIPTION);
    }
}
