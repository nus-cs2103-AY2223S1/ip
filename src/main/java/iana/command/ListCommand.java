package iana.command;

import iana.main.Storage;
import iana.main.TaskList;
import iana.main.Ui;

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