package duke.response;

import duke.DukeException;

/**
 * A DukeResponse for an exception.
 */
public class ExceptionResponse extends DukeResponse {
    protected DukeException exception;

    /**
     * Constructor for an ExceptionResponse.
     *
     * @param exception The exception.
     */
    public ExceptionResponse(DukeException exception) {
        this.exception = exception;
    }

    @Override
    public String run() {
        String oops = "☹ OOPS!!! ";
        return oops + exception.getMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
