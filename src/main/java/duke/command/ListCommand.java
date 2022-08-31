package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to list all tasks from the task list.
 *
 * @author Tan Jun Wei
 */
public class ListCommand extends Command {
    /**
     * Constructs a list command.
     */
    public ListCommand() {}

    /**
     * Outputs the list of tasks.
     *
     * @param tasks The task list to be operated on.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showOutput("Here are the tasks in your list: \n" + tasks);
    }
}
