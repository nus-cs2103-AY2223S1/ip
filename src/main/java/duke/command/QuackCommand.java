package duke.command;

import static duke.constants.Constants.QUACK_STRING;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * When the user types quack, we need to quack back
 *
 *
 */
public class QuackCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return QUACK_STRING;
    }
}
