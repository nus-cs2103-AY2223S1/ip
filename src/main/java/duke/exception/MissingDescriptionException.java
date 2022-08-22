package duke.exception;

import duke.exception.DukeException;

public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String description) {
        super(description);
    }
}
