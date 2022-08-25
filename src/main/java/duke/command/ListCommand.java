package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks.getAllTasks());
    }
}
