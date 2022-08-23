package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.commands.Command;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Uncle really don't understand.");
    }
}
