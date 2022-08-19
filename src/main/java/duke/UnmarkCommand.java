package duke;

public class UnmarkCommand extends Command {

    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = tasks.unmark(taskNumber);
        ui.showUnmarkedTask(unmarkedTask);
        tasks.updateStorage(storage);
    }
}
