package pony.command;

import pony.Storage;
import pony.TaskList;
import pony.Ui;

/**
 * Command to list all the tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes a list command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printTaskList(tasks);
    }
}
