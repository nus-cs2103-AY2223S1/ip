public class DeleteCommand extends Command{

    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deleted = tasks.deleteTask(idx);
        ui.showDeleteTask(deleted, tasks);
    }
}
