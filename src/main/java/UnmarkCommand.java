public class UnmarkCommand implements Command{

    private final int toUnmark;

    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.unmark(toUnmark));
        storage.refresh(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
