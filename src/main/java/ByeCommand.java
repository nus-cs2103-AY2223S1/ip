public class ByeCommand extends Command {
    public ByeCommand() {
        this.setExit();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.close();
    }
}
