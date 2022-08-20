package duke.exception;

import duke.exception.DukeException;

public class InvalidDateException extends DukeException {
    public InvalidDateException(String msg) {
        super(msg);
    }
}
