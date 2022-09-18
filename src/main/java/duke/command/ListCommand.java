package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Class which manages listing all tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.showTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
