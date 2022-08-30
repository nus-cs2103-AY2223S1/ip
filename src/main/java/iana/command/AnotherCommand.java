package iana.command;

import iana.main.Storage;
import iana.main.TaskList;
import iana.main.Ui;

public class AnotherCommand extends Command {
    public AnotherCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.askNewCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
