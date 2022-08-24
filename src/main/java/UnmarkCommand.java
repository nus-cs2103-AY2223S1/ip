public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.get(index).notFinished();
        Task task = tasks.get(index);
        ui.showUnmark(task);
        storage.save(tasks);
    }
}
