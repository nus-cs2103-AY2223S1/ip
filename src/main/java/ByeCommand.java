public class ByeCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
