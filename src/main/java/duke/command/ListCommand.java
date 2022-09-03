package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command to list all tasks in a task list.
 */
public class ListCommand extends Command {
    /**
     * Returns a string listing all the tasks in a task list.
     *
     * @param tasks TaskList object to list.
     * @param storage Storage object to manage save file.
     * @return String listing all the tasks in a task list.
     * @throws DukeException If task list is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return Ui.getTaskListString(tasks);
    }
}
