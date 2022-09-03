package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * List all the tasks that the user has
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.listAllTasks(tasks);
    }
}
