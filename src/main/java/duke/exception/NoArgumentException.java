package duke.exception;

import duke.util.Ui;

public class NoArgumentException extends DukeException {
    public NoArgumentException(int commandIndex, Throwable e) {
        super("The proper command is: " + Ui.COMMAND_HELP[commandIndex], e);
    }
}
