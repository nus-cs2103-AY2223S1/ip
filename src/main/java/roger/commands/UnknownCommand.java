package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Uncle really don't understand.");
    }
}
