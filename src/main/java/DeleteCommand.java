public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(this.index);
        storage.overwriteData(tasks);
        ui.notifyDeleted(tasks.get(this.index));
    }
}
