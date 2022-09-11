package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;

/**
 * ShowListCommand class handles the command to list all the tasks.
 */
public class ShowListCommand extends Command {

    /**
     * {@inheritDoc}
     * Lists down all the tasks in the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTaskString(tasks.getList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
