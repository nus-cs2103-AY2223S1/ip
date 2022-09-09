package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.Constants.INVALID_COMMAND_MESSAGE;

/**
 * When the command is not recognised
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {

    }
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return INVALID_COMMAND_MESSAGE;
    }
}
