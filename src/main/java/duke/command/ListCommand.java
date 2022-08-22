package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }
}
