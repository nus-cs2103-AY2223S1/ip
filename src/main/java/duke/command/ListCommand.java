package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list all tasks currently in the list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
