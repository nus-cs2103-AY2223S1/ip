public class ListCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.list());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
