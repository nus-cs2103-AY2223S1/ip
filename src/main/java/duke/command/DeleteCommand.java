package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    int taskNumber;

    /**
     * Initialises the DeleteCommand with the task number of task to be deleted.
     * @param taskNumber Task number of task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this command to delete a task from the list and update storage.
     * @param tasks Task list that task at task number is deleted from.
     * @param ui UI to display success on deletion.
     * @param storage Storage to be updated after deleting task.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(this.taskNumber);
        storage.update(tasks);
        ui.showDeleteMessage(deletedTask, tasks.getNumTasks());
    };

    /**
     * Checks if Duke application should exit after this command.
     * @return False as this command is not bye.
     */
    @Override
    public boolean isExit() {
        return false;
    };

}
