public class MarkCommand extends Command{
    public static final String COMMAND_WORD = "mark";
    private int index;
    public MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.markDone(index);
        storage.save(tasks.getTaskList());
        ui.showMark(t);
    }
}
