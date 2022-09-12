package duke;

/**
 * Encapsulate the command that allow users to delete existing task from the TaskList,
 * which is-a Command.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Constructs an instance of DeleteCommand which inherits from Command.
     *
     * @param taskNumber number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.delete(taskNumber);
        tasks.updateStorage(storage);
        return ui.showDeletedTask(deletedTask, tasks);
    }

}
