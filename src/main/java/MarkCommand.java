public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int idx) {
        super();
        this.index = idx;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > t.getSize()) {
            throw new DukeException(Constants.INVALID_INDEX);
        }
        Task tsk = t.markTask(index);
        storage.writeFile(t.tasksToString());
        ui.printMarkTask(tsk);
    }
}
