package uwu.command;

import uwu.Storage;
import uwu.task.TaskList;
import uwu.Ui;

public class ListCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    };

    public boolean isExit() {
        return false;
    };
}
