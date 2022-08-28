package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the command to delete a Task from the list of tasks.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskIndex;

    /**
     * Constructor for a DeleteCommand.
     * @param taskNum The task number of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        super();
        taskIndex = taskNum - 1;
    }

    /**
     * Checks if the Command is a ByeCommand.
     * @return False.
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskList List of tasks.
     * @param ui Shows the Task removed and the total number of tasks on the list.
     * @param storage Saves the modified list of tasks.
     * @throws DukeException If an invalid task number is provided by the user or if there
     *         is an error saving the modified list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(taskIndex);
        storage.save(taskList);
        ui.showTaskRemoved(deletedTask);
    }
}
