public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodbye();
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
