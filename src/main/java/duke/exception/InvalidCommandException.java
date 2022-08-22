package duke.exception;

import duke.exception.DukeException;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String description) {
        super(description);
    }
}
