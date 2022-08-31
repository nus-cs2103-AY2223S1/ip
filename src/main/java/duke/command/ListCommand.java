package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of displaying all tasks in the current task list in Duke.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showList(tasks.getTasks());
    }

}
