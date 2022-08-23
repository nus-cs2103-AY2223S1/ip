package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.TaskList;

/**
 * Representation of all invalid commands
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public InvalidCommand(String errorFormat, Object... args) {
        this.errorMessage = String.format(errorFormat, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        throw new DukeException(this.errorMessage);
    }
}
