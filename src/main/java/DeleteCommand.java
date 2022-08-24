public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toBeRemoved = tasks.get(idx - 1);
        ui.DeleteTask(tasks, toBeRemoved);
        tasks.remove(idx - 1);
        storage.update(tasks.getTasks());
    }
}
