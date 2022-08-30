package iana.command;

import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

public class ListCommand extends Command {
    
    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}