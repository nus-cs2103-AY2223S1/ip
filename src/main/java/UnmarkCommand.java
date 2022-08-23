public class UnmarkCommand extends Command{
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.unmarkTask(idx);
        ui.showUnmarkTask(task);
    }
}
