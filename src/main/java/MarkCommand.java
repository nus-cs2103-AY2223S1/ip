public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.changeDoneStatus(this.index, this.isDone);
        storage.overwriteData(tasks);
        ui.notifyMarked(tasks.get(this.index), this.isDone);
    }
}
