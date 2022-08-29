package exception;

import exception.DukeException;

public class DukeInvalidCommandException extends DukeException {
    public DukeInvalidCommandException(String command) {
        super("command.Command " + command + " not found. Please try again.");
    }
}
