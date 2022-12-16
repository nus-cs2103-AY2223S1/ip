package commands;

import exception.FredException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * List command for listing out all tasks from taskList.
 */
public class ListCommand extends Command {

    /**
     * Create ListCommand
     */
    public ListCommand() {
        isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        ui.storeMessage(tasks.list());
    }
}
