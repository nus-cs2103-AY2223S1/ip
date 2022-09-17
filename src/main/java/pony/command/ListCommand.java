package pony.command;

import pony.Storage;
import pony.task.TaskList;
import pony.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printTaskList(tasks);
    }
}
