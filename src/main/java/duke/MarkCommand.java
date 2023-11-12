package duke;

public class MarkCommand extends Command {

    private int taskNumber;
    private boolean isMark;

    public MarkCommand(int taskNumber, boolean isMark) {
        super();
        this.taskNumber = taskNumber;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (isMark) {
            tasks.markTask(taskNumber);
            storage.save(tasks);
            return ui.showMarkTask();
        } else {
            tasks.unmarkTask(taskNumber);
            storage.save(tasks);
            return ui.showUnmarkTask();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
