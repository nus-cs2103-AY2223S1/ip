public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        ui.showTasks(tasks);
    }
}
