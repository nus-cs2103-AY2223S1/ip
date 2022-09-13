package command;

import exceptions.DukeException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Command that handles adding Task to TaskList and Storage.
 */
public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }
    /**
     * Runs when task is to be added.
     *
     * @param taskList TaskList to append Task to.
     * @param ui ui provides user command.
     * @param storage Storage space to append Task to.
     * @throws DukeException When parsing user command fails.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String storableLine = task + "\n";
        if (storage.isLineAppended(storableLine)) {
            taskList.addTask(task);
            ui.showMessage("Added task");
        }
    }
}
