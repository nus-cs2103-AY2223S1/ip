package duke.exception;

import duke.Ui;

public class DukeInvalidException extends DukeException {
    public DukeInvalidException() {
        super("OOPS! I'm sorry, but I don't know what that means.\n" + Ui.promptUserInput());
    }
}
