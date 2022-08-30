package iana.command;

import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

/**
 * Command to list out all current tasks in task list.
 */
public class ListCommand extends Command {
    
    /**
     * Runs command to list out all current tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks);
    }

    /**
     * Returns false as command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}