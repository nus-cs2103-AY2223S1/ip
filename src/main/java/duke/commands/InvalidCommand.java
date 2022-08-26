package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class InvalidCommand extends Command {
    private final String errorMsg;

    public InvalidCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
