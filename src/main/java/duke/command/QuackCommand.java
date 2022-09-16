package duke.command;

import static duke.constants.Constants.QUACK_STRING;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Returns a quack back to the user
 */
public class QuackCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return QUACK_STRING;
    }
}
