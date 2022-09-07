package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        StringBuilder resPara = new StringBuilder("Your list of tasks: \n");
        if (tasks.getTaskCount() == 0) {
            return "You have no tasks added!";
        }
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task currTask = tasks.getList().get(i);
            resPara.append(i + 1).append(": ").append(currTask.toString()).append("\n");
        }
        return resPara.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
