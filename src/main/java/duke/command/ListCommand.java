package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to list all tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand to list all tasks in the list.
     *
     * @param tasks TaskList that contains all tasks.
     * @param ui Ui that displays tasks to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}
