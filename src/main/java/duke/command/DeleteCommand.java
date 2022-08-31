package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of deleting a specific task in the task list.
 */
public class DeleteCommand extends Command {

    private final int indexOfTaskToDelete;

    /**
     * Constructs a DeleteCommand instance
     *
     * @param indexOfTaskToDelete index that specifies the position of the task that needs to be deleted.
     */
    public DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        if (indexOfTaskToDelete <= 0 || indexOfTaskToDelete > tasks.getNumOfRemainingTasks()) {
            throw new InvalidIndexException();
        }

        Task taskToDelete = tasks.deleteTask(indexOfTaskToDelete);
        storage.save(tasks);
        ui.showDeletedTask(taskToDelete, tasks.getNumOfRemainingTasks());
    }

}
