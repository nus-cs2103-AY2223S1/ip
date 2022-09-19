package iana.command;

import iana.tasks.TaskList;
import iana.ui.Ui;

public class ReminderCommand extends Command {
    
    @Override
    public String execute(TaskList tasks, Ui ui) {
        TaskList incompleteList = tasks.getIncompleteTasks();
        return ui.list(incompleteList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
