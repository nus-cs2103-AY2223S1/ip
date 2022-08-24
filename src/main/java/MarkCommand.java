public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.get(index).finished();
        Task task = tasks.get(index);
        ui.showMark(task);
        storage.save(tasks);
    }
}
