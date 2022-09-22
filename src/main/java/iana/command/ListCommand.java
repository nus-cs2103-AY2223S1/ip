package iana.command;

import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command to list out all current tasks in task list.
 */
public class ListCommand extends Command {
    
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.list(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}