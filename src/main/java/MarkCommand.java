public class MarkCommand extends Command{

    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTask(idx);
        ui.showMarkTask(task);
    }
}
