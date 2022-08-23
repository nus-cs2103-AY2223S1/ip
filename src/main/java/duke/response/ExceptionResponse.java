package duke.response;

import duke.DukeException;

public class ExceptionResponse extends DukeResponse {
    protected DukeException exception;

    public ExceptionResponse(DukeException exception) {
        this.exception = exception;
    }

    @Override
    public void run() {
        String OOPS = "☹ OOPS!!! ";
        super.message(OOPS + exception.getMessage());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}