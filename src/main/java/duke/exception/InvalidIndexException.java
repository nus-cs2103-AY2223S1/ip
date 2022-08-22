package duke.exception;

import duke.exception.DukeException;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String description) {
        super(description);
    }
}
