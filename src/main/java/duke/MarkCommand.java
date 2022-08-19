package duke;

public class MarkCommand extends Command {

    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.mark(taskNumber);
        ui.showMarkedTask(markedTask);
        tasks.updateStorage(storage);
    }
}
