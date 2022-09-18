package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Class which manages listing all tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.showTaskList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
