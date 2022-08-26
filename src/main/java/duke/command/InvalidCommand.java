package duke.command;

import duke.storage.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * When the command is not recognised
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {

    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {

    }
}
