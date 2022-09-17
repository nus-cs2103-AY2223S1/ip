package pony.command;

import pony.Storage;
import pony.task.TaskList;
import pony.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printExit();
    }
}
