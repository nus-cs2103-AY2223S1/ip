public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.storeTasks(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
