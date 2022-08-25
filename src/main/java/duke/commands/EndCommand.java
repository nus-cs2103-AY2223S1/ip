package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to terminate the program
 */
public class EndCommand extends Command {

    /**
     * Executes the command.
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        return;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
