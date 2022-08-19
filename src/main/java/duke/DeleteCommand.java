package duke;

public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.delete(taskNumber);
        ui.showDeletedTask(deletedTask, tasks);
        tasks.updateStorage(storage);
    }

}
