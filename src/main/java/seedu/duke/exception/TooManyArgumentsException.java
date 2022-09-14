package seedu.duke.exception;

import seedu.duke.Duke;

public class TooManyArgumentsException extends DukeException {
    public TooManyArgumentsException(String command) {
        super("Please input only '" + command + "' and a number, Master.");
    }
}
