package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;

/**
 * ListCommand is the Command when the user wants the list of the user's current tasks.
 */
public class ListCommand extends Command {

    /**
     * Displays the user's tasks.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            throw new DukeException("OOPS!!! You have no tasks in the list.");
        }
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            ui.showMessage((i + 1) + "." + task);
        }
    }
}
