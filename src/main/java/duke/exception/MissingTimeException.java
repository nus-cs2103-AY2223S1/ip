package duke.exception;

import duke.exception.DukeException;

public class MissingTimeException extends DukeException {
    public MissingTimeException(String description) {
        super(description);
    }
}
