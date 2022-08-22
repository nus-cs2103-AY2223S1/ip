package duke.exception;

import duke.exception.DukeException;

public class MissingIndexException extends DukeException {
    public MissingIndexException(String description) {
        super(description);
    }
}
