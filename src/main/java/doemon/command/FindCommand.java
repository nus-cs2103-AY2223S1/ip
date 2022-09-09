package doemon.command;

import java.util.ArrayList;

import doemon.storage.Storage;
import doemon.task.Task;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Command to find tasks that match a user-inputted keyword.
 */
public class FindCommand extends Command {
    /** String to be used to find matching tasks. */
    private String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param keyword String to be used to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTasks(this.keyword);
        ui.showFoundTasks(foundTasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
