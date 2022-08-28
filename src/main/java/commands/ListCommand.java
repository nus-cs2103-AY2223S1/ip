package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMultiMsg(tasks.toStringArr());
    }
}
