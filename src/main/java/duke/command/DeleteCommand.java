package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to delete a task to the task list
 *
 * @author Pontakorn Prasertsuk
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a new DeleteCommand instance.
     *
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand
     *
     * @param taskList the task list to be mutated
     * @param ui the user interface to be used
     * @param storage the storage to be used
     * @return output to be shown
     * @throws DukeException if an error occurs
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(index);
        ui.showOutput("Task " + (index + 1) + " has been deleted!");
        storage.save(taskList.getTaskList());

        return "Task " + (index + 1) + " has been deleted!";
    }

    /**
     * Returns false as this is not the exit command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
