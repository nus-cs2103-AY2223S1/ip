package duke.exception;

import duke.exception.DukeException;

public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String description) {
        super(description);
    }
}
