package duke;

/**
 * Encapsulate the command that allow users to delete existing task from the TaskList,
 * which is-a Command.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Class constructor for DeleteCommand.
     *
     * @param taskNumber number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Delete existing task int the tasklist and update the data file.
     *
     * @param tasks list of existing tasks.
     * @param ui user interface to be shown.
     * @param storage to rewrite the data file.
     * @throws DukeException if something went wrong with the update of data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.delete(taskNumber);
        ui.showDeletedTask(deletedTask, tasks);
        tasks.updateStorage(storage);
    }

}
