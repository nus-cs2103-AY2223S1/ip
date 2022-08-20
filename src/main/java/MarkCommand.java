public class MarkCommand extends Command {

    private int taskNumber;
    private boolean isMark;

    public MarkCommand(int taskNumber, boolean isMark) {
        super();
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isMark) {
            tasks.markTask(taskNumber);
            ui.showMarkTask();
        } else {
            tasks.unmarkTask(taskNumber);
            ui.showUnmarkTask();
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
