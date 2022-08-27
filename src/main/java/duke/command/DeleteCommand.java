package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that is executed when the user inputs delete.
 *
 * @author njxue
 * @version v0.1
 */
public class DeleteCommand extends Command {
    /**
     * The index of the Task object in the TaskList to be deleted.
     */
    private int taskIndex;

    /**
     * Creates a DeleteCommand.
     *
     * @param taskIndex Index of the Task object in the TaskList to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Returns the format of the delete command.
     *
     * @return The format of the delete command.
     */
    public static String getFormat() {
        return "delete <Integer>";
    }

    /**
     * Executes the delete command. Deletes the specific Task object in the TaskList.
     *
     * @param tasks TaskList containing the task to be deleted.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @throws DukeException If storage object is unable to save the list of tasks after marking,
     *              or if the taskIndex is not within range of the size of the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(taskIndex);
        ui.showDeleteTask(task, tasks);
        storage.save(tasks);
    }

    /**
     * Returns false, because delete is not an application terminating command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
