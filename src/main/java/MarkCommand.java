import java.io.IOException;

public class MarkCommand extends Command{
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TakoException {
        tasks.mark(taskNumber);
        Task task = tasks.get(taskNumber);
        storage.saveToFile(tasks);
        ui.showMark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
