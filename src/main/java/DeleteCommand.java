public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(index);
        ui.deleteSuccess(task, tasks);
        storage.save(tasks);
    }
}
