package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as not done in the task list.
 *
 * @author Rama Aryasuta Pangestu
 */
public class UnMarkCommand extends Command {
    private final int index;

    /**
     * Constructs a command to mark the task with index
     * <code>index</code> as not done in the task list.
     *
     * @param index the index of the task marked as not done in the task list
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task at index <code>index</code> as not done in the task list.
     *
     * @param ui       the user interface
     * @param storage  the storage dealing with loading and saving tasks in the save file
     * @param taskList the task list
     * @throws DukeException if the index is out of bounds or an error occurs when saving the task list
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.unMarkTask(index);
        ui.addOutput("OK, I've marked this task as not done yet:\n  " + task + "\n");
        storage.save(taskList);
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
