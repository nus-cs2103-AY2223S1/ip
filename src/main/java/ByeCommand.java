public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.close();
    }
}
