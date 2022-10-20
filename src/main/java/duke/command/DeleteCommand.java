package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of deleting a specific task in the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND = "DELETE";

    public static final String MESSAGE_USAGE = COMMAND
                + "\nDelete a task with specific index, delete <index>"
                + "\nExample: delete 1";
    private static final int OFFSET = -1;
    private final int indexOfTaskToDelete;

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param indexOfTaskToDelete index that specifies the position of the task that needs to be deleted
     */
    public DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete + OFFSET;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {

        if (!isValidIndex(tasks)) {
            ui.showIndexOutOfBound(tasks.getNumOfRemainingTasks());
            return;
        }

        Task taskToDelete = tasks.deleteTask(indexOfTaskToDelete);
        storage.save(tasks);
        ui.showDeletedTask(taskToDelete, tasks.getNumOfRemainingTasks());
    }

    private boolean isValidIndex(TaskList tasks) {
        if (indexOfTaskToDelete < 0 || indexOfTaskToDelete >= tasks.getNumOfRemainingTasks()) {
            return false;
        }
        return true;
    }

}
