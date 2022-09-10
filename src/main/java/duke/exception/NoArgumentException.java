package duke.exception;

import duke.util.Ui;

/**
 * Thrown when a command is used without it's required arguments.
 */
public class NoArgumentException extends DukeException {
    public NoArgumentException(String command, Throwable e) {
        super("The proper command is: \n" + Ui.COMMAND_HELP.get(command), e);
    }
}
