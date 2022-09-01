public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.store();
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
