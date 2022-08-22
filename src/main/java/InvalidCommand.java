public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showInvalidCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
