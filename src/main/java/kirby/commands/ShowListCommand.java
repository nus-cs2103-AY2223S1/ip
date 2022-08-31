package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Task;

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
        System.out.println("Here is your bag of fabulous tasks:");
        String resPara = "Your list of tasks: \n";
        if (tasks.getTaskCount() == 0) {
            return "You have no tasks added!";
        }
        for (int i = 0; i < tasks.getList().size(); i++) {
            Task currTask = tasks.getList().get(i);
            resPara += i + 1 + ": " + currTask.toString() + "\n";
        }
        return resPara;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
