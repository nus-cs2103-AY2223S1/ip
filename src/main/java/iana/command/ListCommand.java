package iana.command;

import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command to list out all current tasks in task list.
 */
public class ListCommand extends Command {
    
    /**
     * Runs command to list out all current tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.list(tasks);
    }

    /**
     * Returns false as command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}